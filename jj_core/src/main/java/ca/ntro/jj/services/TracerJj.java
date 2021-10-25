package ca.ntro.jj.services;

import ca.ntro.jj.initialization.ServiceRequester;
import ca.ntro.jj.initialization.ServiceDependant;
import ca.ntro.jj.values.ServiceMap;

public class TracerJj extends Tracer implements ServiceDependant {
	
	private Logger logger = new LoggerNull();
	private StackAnalyzer stackAnalyzer = new StackAnalyzerNull();

	@Override
	public void requestServices(ServiceRequester registrar) {
		registrar.requestService(logger.serviceId());
		registrar.requestService(stackAnalyzer.serviceId());
	}

	@Override
	public void handleServices(ServiceMap services) {
		logger = services.getService(logger.serviceId());
		stackAnalyzer = services.getService(stackAnalyzer.serviceId());
	}

	@Override
	public void trace(Object calledClassOrObject, Object[] arguments) {

		// TODO: actual stacktrace analysis
		stackAnalyzer.analyzeCall(calledClassOrObject);

		logger.trace("TODO");
	}

}
