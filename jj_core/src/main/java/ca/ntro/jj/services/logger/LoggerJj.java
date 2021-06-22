package ca.ntro.jj.services.logger;


import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ClassIdJj;
import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.services.tracer.TracerNull;

public abstract class LoggerJj implements Logger, InitializedObject {
	
	private ClassId<Tracer> tracerDescriptor = new ClassIdJj<>(Tracer.class);
	private Tracer tracer = new TracerNull();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(tracerDescriptor);
	}

	@Override
	public void initialize(ObjectMap arguments) {
		tracer = arguments.getSingleton(tracerDescriptor);
	}

}
