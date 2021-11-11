package ca.ntro.server;

import ca.ntro.core.initialization.ServiceRequester;
import ca.ntro.core.initialization.ServiceDependant;
import ca.ntro.core.values.ServiceMap;

public abstract class ServerNtro implements Server, ServiceDependant {

	//private Logger logger = new NullLogger();
	//private Options options = new OptionsNtro();
	private ServerOptions serverOptions = new ServerOptionsNtro();

	@Override
	public void requestServices(ServiceRequester registrar) {
		//registrar.addDependency(Logger.classId());
		//registrar.addDependency(Options.classId());
		//registrar.requestService(serverOptions.serviceId());
	}

	@Override
	public void handleServices(ServiceMap resolvedDependencies) {
		//logger = resolvedDependencies.getSingleton(Logger.classId());
		//options = resolvedDependencies.getSingleton(Options.classId());
		//serverOptions = resolvedDependencies.getSingleton(ServerOptions.classId());
	}

	protected Logger logger() {
		//return logger;
		return null;
	}

	protected ServerOptions serverOptions() {
		return serverOptions;
	}

	protected Options options() {
		//return options;
		return null;
	}
}
