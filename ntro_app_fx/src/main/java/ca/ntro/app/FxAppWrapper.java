package ca.ntro.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class FxAppWrapper extends Application {
	
	static Class<? extends NtroAppFx> appClass;

	@Override
	public void start(Stage primaryStage) throws Exception {
		NtroAppFx.start(primaryStage);
	}

}
