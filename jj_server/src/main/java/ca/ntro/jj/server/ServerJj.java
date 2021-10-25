package ca.ntro.jj.server;

import ca.ntro.jj.initialization.ServiceRequester;
import ca.ntro.jj.initialization.ServiceDependant;
import ca.ntro.jj.values.ObjectMap;

public abstract class ServerJj implements Server, ServiceDependant {

	//private Logger logger = new NullLogger();
	//private Options options = new OptionsJj();
	private ServerOptions serverOptions = new ServerOptionsJj();

	@Override
	public void requestServices(ServiceRequester registrar) {
		//registrar.addDependency(Logger.classId());
		//registrar.addDependency(Options.classId());
		registrar.requestService(ServerOptions.classId());
	}

	@Override
	public void handleServices(ObjectMap resolvedDependencies) {
		//logger = resolvedDependencies.getSingleton(Logger.classId());
		//options = resolvedDependencies.getSingleton(Options.classId());
		serverOptions = resolvedDependencies.getSingleton(ServerOptions.classId());
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
