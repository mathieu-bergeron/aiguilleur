package ca.ntro.jj.demo.jsweet;

import ca.jj.demo.core.Demo;
import ca.ntro.jj.core.singletons.T;
import ca.ntro.jj.jsweet.globals.JjJSweet;

public class DevJSweet {
	
	public static void main(String[] args) {

		JjJSweet.initializator()
		        .execute()
		        .handleResult(r -> {
		        	T.trace(DevJSweet.class);

					Demo.main();
				 });
		}
}
