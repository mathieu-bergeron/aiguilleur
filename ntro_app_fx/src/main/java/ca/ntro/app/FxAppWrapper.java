package ca.ntro.app;

import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.frontend.WindowFx;
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

		WindowFx window = new WindowFx(primaryStage);

		FrontendRegistrarFx frontendRegistrar = new FrontendRegistrarFx(window);
		app.registerFrontend(frontendRegistrar);

		frontendRegistrar.executeFrontendTasks();
	}

}
