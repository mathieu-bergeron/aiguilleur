package ca.ntro.jj.services.logger;


import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.services.tracer.NullTracer;

public abstract class LoggerJj implements Logger, InitializedObject {
	
	private Tracer tracer = new NullTracer();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(Tracer.classId());
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		tracer = resolvedDependencies.getSingleton(Tracer.classId());
	}
}
