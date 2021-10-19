package ca.jj.core.globals;


public class Jj {

	public static void log(String string) {
		System.out.println("LOG: " + string);
	}


	public static void info(String string) {
		System.out.println("INFO: " + string);
	}

	public static <O extends Object> O newInstance(Class<O> _class) {

		try {

			return (O) _class.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		return null;
	}

}
