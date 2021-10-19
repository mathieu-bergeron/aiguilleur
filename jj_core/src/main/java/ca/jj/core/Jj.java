package ca.jj.core;


public class Jj {

	public static void log(String string) {
		System.out.println("LOG: " + string);
	}

	public static void trace(Object callerObjectOrClass) {
		System.out.println("TRACE: " + callerObjectOrClass);
		
	}

	public static void info(String string) {
		System.out.println("INFO: " + string);
	}

}
