package ca.ntro.jj.managed_objects;

public class FactoryJj implements Factory {
	
	private static final FactoryJj instance = new FactoryJj();
	
	private FactoryJj() {
	}
	
	public static Factory getInstance() {
		return instance;
	}


}
