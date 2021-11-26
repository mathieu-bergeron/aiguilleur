package ca.ntro.core.demo.jsweet;

import ca.ntro.core.demo.Demo;
import ca.ntro.core.initialization.NtroJSweet;
import ca.ntro.core.initialization.T;

public class DevJSweet {
	
	public static void main(String[] args) {

		NtroJSweet.initializer()
		          .executeAsync()
		          .handleResult(r -> {
		        	  T.trace(DevJSweet.class);

					  Demo.main();
				 });
		}
}
