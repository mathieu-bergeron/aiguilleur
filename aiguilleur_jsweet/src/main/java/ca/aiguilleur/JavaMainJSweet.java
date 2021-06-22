package ca.aiguilleur;

import ca.ntro.jj.AppJSweet;
import ca.ntro.jj.app.Log;
import ca.ntro.jj.app.Options;
import ca.ntro.jj.app.OptionsJj;

public class JavaMainJSweet {
	
	public static void main(String[] args) {

		Options options = new OptionsJj();
		options.setIsProd(false);

		new AppJSweet().addOptions(options)

				       .initialize()

					   .onAppInitialized(() -> {
						   
						   Log.text("Shortcut classes like Log and T are now initialized");
						   Log.text("NOTE: other services should be added as dependencies in each Controller");
						   Log.text("e.g. RegEx might not be needed, and hence we don't want to waste time loading its .js and compiling it");

						   AiguilleurMain.main();
						})

					    .onInitializationFailure(e -> {

							e.printStackTrace();

					    });
		}
}
