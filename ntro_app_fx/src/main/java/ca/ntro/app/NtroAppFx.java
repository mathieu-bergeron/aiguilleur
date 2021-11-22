package ca.ntro.app;

import ca.ntro.app.App;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.static_imports.NtroJdk;
import javafx.application.Application;
import javafx.stage.Stage;

public interface NtroAppFx extends App<ViewRegistrarFx> {
	
	
	public static void launch(String[] args) {

		try {

			NtroJdk.initializer().executeBlocking();

		} catch (Throwable e) {

			throw new RuntimeException(e);
		}
		
		FxAppWrapper.appClass = (Class<? extends NtroAppFx>) Ntro.stackAnalyzer().callerClass();
		
		System.out.println(FxAppWrapper.appClass);
		
		Application.launch(FxAppWrapper.class, args);
	}

	public static void start(Stage primaryStage) {
		
		// Instantiate
		NtroAppFx app = null;
		try {

			app = FxAppWrapper.appClass.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			
			Ntro.exceptionThrower().throwException(e);
		}
		
		// call the register methods
		FrontendRegistrarFx frontendRegistrar = new FrontendRegistrarFx();
		app.registerFrontend(frontendRegistrar);
		

		primaryStage.setScene(frontendRegistrar.viewRegistrar().rootScene());
		primaryStage.show();
	}

}
