package ca.ntro.core.services;

import ca.ntro.core.files.LocalTextFile;
import ca.ntro.core.initialization.ServiceRequester;
import ca.ntro.core.path.Path;
import ca.ntro.core.initialization.ServiceDependant;
import ca.ntro.core.values.ServiceMap;

public class LoggerNtro extends Logger implements ServiceDependant {

	private Path traceFilePath = Path.emptyPath();                        // TODO: actual file path
	private Path exceptionFilePath = Path.emptyPath();                    // TODO: actual file path

	private FileOpener fileOpener = new FileOpenerNull();

	@Override
	public void requestServices(ServiceRequester requester) {
		requester.requestService(fileOpener.serviceId());
	}

	@Override
	public void handleServices(ServiceMap services) {
		fileOpener = services.getService(fileOpener.serviceId());
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
