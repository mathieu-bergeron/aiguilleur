package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.values.Path;

public class LoggerJj implements Logger, InitializedObject {

	private Path traceFilePath = new Path();                        // TODO: actual file path
	private Path exceptionFilePath = new Path();                    // TODO: actual file path

	private FileOpener fileOpener = new FileOpenerNull();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(FileOpener.classId());
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		fileOpener = resolvedDependencies.getSingleton(FileOpener.classId());
	}

	@Override
	public void exception(Throwable e) {

		LocalTextFile exceptionFile = fileOpener.openLocalTextFile(exceptionFilePath);

		// TODO: better formatting
		exceptionFile.append(e.getMessage()).handleException(e2 -> {
			e2.printStackTrace();
		});
	}

	@Override
	public void trace(String message) {

		LocalTextFile traceFile = fileOpener.openLocalTextFile(traceFilePath);

		traceFile.append(message).handleException(e -> {
			exception(e);
		});
	}
}
