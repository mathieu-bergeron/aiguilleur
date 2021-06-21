package ca.ntro.jj.services.tracer;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.managed_objects.ObjectDescriptor;
import ca.ntro.jj.tasks.results.ObjectMap;

public class TracerNull implements Tracer {

	@Override
	public List<ObjectDescriptor<?>> dependencies() {
		return new ArrayList<>();
	}

	@Override
	public void initialize(ObjectMap arguments) {
	}

	@Override
	public TracedCall traceCall(int stackTraceIncrement) {
		return new TracedCallNull();
	}

}
