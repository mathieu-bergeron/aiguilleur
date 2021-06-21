package ca.ntro.jj.services.logger;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.managed_objects.Factory;
import ca.ntro.jj.managed_objects.ObjectDescriptor;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.services.tracer.TracerNull;
import ca.ntro.jj.tasks.results.ObjectMap;

public abstract class LoggerJj implements Logger {
	
	private ObjectDescriptor<Tracer> tracerDescriptor = new ObjectDescriptorJj(Tracer.class, Factory.SINGLETON_ID);
	private Tracer tracer = new TracerNull();

	@Override
	public List<ObjectDescriptor<?>> dependencies() {

		List<ObjectDescriptor<?>> dependencies = new ArrayList<>();
		
		dependencies.add(tracerDescriptor);

		return dependencies;
	}

	@Override
	public void initialize(ObjectMap arguments) {
		tracer = arguments.get(tracerDescriptor);
	}

}
