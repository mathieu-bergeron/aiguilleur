package ca.ntro.jj.core.singletons;

public class T {
	
	private static Tracer tracer = new NullTracer();
	

	public static void trace(Object callerObjectOrClass, Object... arguments) {
		tracer.trace(callerObjectOrClass, arguments);
		System.out.println("TRACE: " + callerObjectOrClass);
	}
}
