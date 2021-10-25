package ca.jj.core.singletons;

public class T {
	
	private static Tracer tracer;

	public static void trace(Object callerObjectOrClass) {
		System.out.println("TRACE: " + callerObjectOrClass);
	}
}
