package ca.aiguilleur;

import ca.jj.core.globals.T;
import ca.jj.jdk.globals.JjJdk;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiguilleurMainFx extends Application {
	
	static {
		JjJdk.initializeBlocking();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		T.trace(this);

		// TODO: enregistrer la fenêtre JavaFx

		AiguilleurMain.main();
	}
}
