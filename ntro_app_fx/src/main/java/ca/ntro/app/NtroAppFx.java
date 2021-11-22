package ca.ntro.app;

import ca.ntro.app.App;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.core.static_imports.NtroJdk;

public interface NtroAppFx extends App<ViewRegistrarFx> {
	
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

	}

}
