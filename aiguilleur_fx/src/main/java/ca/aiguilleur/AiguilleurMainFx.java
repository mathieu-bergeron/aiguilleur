package ca.aiguilleur;

import ca.ntro.jj.initialization.T;
import ca.ntro.jj.static_imports.JjJdk;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiguilleurMainFx extends Application {
	
	public static void main(String[] args) throws Throwable {
		JjJdk.initializer().executeBlocking();

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		T.trace(this, primaryStage);

		AiguilleurMain.main();
	}
}
