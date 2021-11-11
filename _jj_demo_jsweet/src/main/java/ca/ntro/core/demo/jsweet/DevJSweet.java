package ca.ntro.core.demo.jsweet;

import ca.jj.demo.core.Demo;
import ca.ntro.core.initialization.JjJSweet;
import ca.ntro.core.initialization.T;

public class DevJSweet {
	
	public static void main(String[] args) {

		JjJSweet.initializer()
		        .execute()
		        .handleResult(r -> {
		        	T.trace(DevJSweet.class);

					Demo.main();
				 });
		}
}
