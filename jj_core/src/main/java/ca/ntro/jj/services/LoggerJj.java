package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.identifyers.Path;
import ca.ntro.jj.initialization.ServiceRequester;
import ca.ntro.jj.initialization.ServiceDependant;
import ca.ntro.jj.values.ServiceMap;

public class LoggerJj extends Logger implements ServiceDependant {

	private Path traceFilePath = new Path();                        // TODO: actual file path
	private Path exceptionFilePath = new Path();                    // TODO: actual file path

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
