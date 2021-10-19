package ca.ntro.jj.demo.jsweet;

import ca.ntro.jj.AppJSweet;
import ca.ntro.jj.app.Log;
import ca.ntro.jj.app.Options;
import ca.ntro.jj.app.OptionsJj;

public class DevJSweet {
	
	public static void main(String[] args) {

		JjJSweet.initializator()
		        .execute()
		        .onResult(r -> {

				   Demo.main();
				 })

				 .onFailure(e -> {

					e.printStackTrace();
				 });
		}
}
