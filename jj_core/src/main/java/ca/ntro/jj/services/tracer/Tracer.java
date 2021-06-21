package ca.ntro.jj.services.tracer;

import ca.ntro.jj.services.Service;

public interface Tracer extends Service {

	TracedCall traceCall(int stackTraceIncrement);

}
