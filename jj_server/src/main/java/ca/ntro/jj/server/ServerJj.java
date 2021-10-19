package ca.ntro.jj.server;

import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;

public abstract class ServerJj implements Server, InitializedObject {

	private Logger logger = new NullLogger();
	private Options options = new OptionsJj();
	private ServerOptions serverOptions = new ServerOptionsJj();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(Logger.classId());
		registrar.addDependency(Options.classId());
		registrar.addDependency(ServerOptions.classId());
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		logger = resolvedDependencies.getSingleton(Logger.classId());
		options = resolvedDependencies.getSingleton(Options.classId());
		serverOptions = resolvedDependencies.getSingleton(ServerOptions.classId());
	}

	protected Logger logger() {
		return logger;
	}

	protected ServerOptions serverOptions() {
		return serverOptions;
	}

	protected Options options() {
		return options;
	}
}
