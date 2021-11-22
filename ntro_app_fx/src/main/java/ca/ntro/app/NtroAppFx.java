package ca.ntro.app;

import ca.ntro.app.App;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.static_imports.NtroJdk;
import javafx.application.Application;

public interface NtroAppFx extends App<ViewRegistrarFx> {
	
	public static void launch(String[] args) {

		try {

			NtroJdk.initializer().executeBlocking();

		} catch (Throwable e) {

			throw new RuntimeException(e);
		}
		
		// Get class of caller
		Class<? extends NtroAppFx> appClass = (Class<? extends NtroAppFx>) Ntro.stackAnalyzer().callerClass();
		
		// Instantiate
		NtroAppFx app = null;
		try {

			app = appClass.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			
			Ntro.exceptionThrower().throwException(e);
		}
		
		// call the register methods
		FrontendRegistrarFx frontendRegistrar = new FrontendRegistrarFx();
		app.registerFrontend(frontendRegistrar);

		// set rootScene
		AppFxWrapper.rootScene = frontendRegistrar.viewRegistrar().rootScene();

		// launch
		Application.launch(AppFxWrapper.class, args);
	}

}
