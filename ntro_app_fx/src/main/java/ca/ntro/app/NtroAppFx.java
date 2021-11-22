package ca.ntro.app;

import ca.ntro.app.App;
import ca.ntro.app.frontend.FxViewRegistrar;
import ca.ntro.core.static_imports.NtroJdk;
import javafx.application.Application;

public interface NtroAppFx extends App<FxViewRegistrar> {
	
	public static void launch(String[] args) {

		try {

			NtroJdk.initializer().executeBlocking();

		} catch (Throwable e) {

			throw new RuntimeException(e);
		}
		
		// Get class of caller
		
		// Instantiate
		
		// call the register methods
		
		// launch
		Application.launch(AppFxWrapper.class, args);
	}

}
