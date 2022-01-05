package ca.ntro.app;

import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.core.initialization.Ntro;
import javafx.application.Application;
import javafx.stage.Stage;

public class FxAppWrapper extends Application {
	
	static Class<? extends NtroAppFx> appClass;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		NtroAppFx app = null;
		try {

			app = appClass.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			
			Ntro.exceptions().throwException(e);
		}

		FrontendRegistrarFx frontendRegistrar = new FrontendRegistrarFx();
		app.registerFrontend(frontendRegistrar);

		primaryStage.show();
		primaryStage.setScene(frontendRegistrar.viewRegistrar().rootScene());
	}

}
