package ca.ntro.jj.demo.jsweet;

import ca.jj.demo.core.Demo;
import ca.ntro.jj.static_imports.JjJSweet;
import ca.ntro.jj.static_imports.T;

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
