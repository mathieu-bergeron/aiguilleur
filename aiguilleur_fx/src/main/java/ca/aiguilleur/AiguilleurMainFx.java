package ca.aiguilleur;

import ca.ntro.jj.static_imports.JjJdk;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiguilleurMainFx extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO: how to integrate more gracefully with JavaFX
		try {

			JjJdk.initializer().executeBlocking();


		} catch (Throwable e) {

			e.printStackTrace();
		}


		AiguilleurMain.main();
	}
}
