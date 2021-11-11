package ca.ntro.jj.initialization;


public class Factory {

	public static <O extends Object> O newInstance(Class<O> _class) {

		try {

			return (O) _class.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		return null;
	}

}