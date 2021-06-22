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

					   .onAppInitialized(services -> {
						   
						   Log.text("Shortcut classes like Log and T are now initialized");

						   AiguilleurMain.main(services);

						})

					    .onInitializationFailure(e -> {

							e.printStackTrace();

					    });
		}
}
