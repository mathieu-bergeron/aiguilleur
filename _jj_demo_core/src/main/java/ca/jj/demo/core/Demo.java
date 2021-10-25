package ca.jj.demo.core;

import ca.ntro.jj.core.singletons.Log;
import ca.ntro.jj.core.singletons.T;

public class Demo {
	
	public static void main() {
		T.trace(Demo.class);

		Log.info("Bonjour!");
	}
}
