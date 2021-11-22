package ca.ntro.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

class AppFxWrapper extends Application {
	
	static Scene rootScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(rootScene);
		primaryStage.show();
	}

}
