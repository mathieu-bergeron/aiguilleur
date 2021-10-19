package ca.ntro.jj.demo.jsweet;

import ca.ntro.jj.AppJSweet;
import ca.ntro.jj.app.Log;
import ca.ntro.jj.app.Options;
import ca.ntro.jj.app.OptionsJj;
import ca.ntro.jj.jsweet.globals.JjJSweet;

public class DevJSweet {
	
	public static void main(String[] args) {

		JjJSweet.initializator()
		        .execute()
		        .onResult(r -> {
		        	T.trace(DevJSweet.class);

					Demo.main();
				 });
		}
}
