package ca.ntro.core.initialization;

import ca.ntro.core.services.Tracer;
import ca.ntro.core.services.TracerNull;

public class T {
	
	private static Tracer tracer = new TracerNull();
	
	static void registerTracer(Tracer tracer) {
		T.tracer = tracer;
	}

	public static void trace(Object callerObjectOrClass, Object... arguments) {
		tracer.trace(callerObjectOrClass, arguments);
	}
}
