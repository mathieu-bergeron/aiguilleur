package ca.ntro.jj.services.tracer;

import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ClassIdJj;

public interface Tracer {

	TracedCall traceCall(int stackTraceIncrement);

	static ClassId<Tracer> classId() {
		return new ClassIdJj<>(Tracer.class);
	}

}
