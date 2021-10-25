package ca.ntro.jj.core.services;

import ca.jj.core.Path;
import ca.ntro.jj.core.files.LocalTextFile;
import ca.ntro.jj.core.values.ObjectMap;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;

public class TracerJj implements Tracer, InitializedObject {
	
	private Path traceFilePath = new Path();                    // TODO: actual file path
	private FileOpener localFiles = new FileOpenerNull();
	private Logger logger = new LoggerNull();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(FileOpener.classId());
		registrar.addDependency(Logger.classId());
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		localFiles = resolvedDependencies.getSingleton(FileOpener.classId());
		logger = resolvedDependencies.getSingleton(Logger.classId());
	}

	@Override
	public void trace(Object calledClassOrObject, Object[] arguments) {

		LocalTextFile traceFile = localFiles.openLocalTextFile(traceFilePath);

		// TODO: actual stacktrace analysis
		traceFile.write("TRACE: " + calledClassOrObject).handleException(e -> {
			logger.exception(e);
		});
	}
}
