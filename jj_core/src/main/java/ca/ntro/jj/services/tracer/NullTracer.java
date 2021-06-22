package ca.ntro.jj.services.tracer;


public class NullTracer implements Tracer {

	@Override
	public TracedCall traceCall(int stackTraceIncrement) {
		return new TracedCallNull();
	}

}
