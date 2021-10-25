package ca.aiguilleur;

import ca.ntro.jj.static_imports.JjJdk;
import ca.ntro.jj.static_imports.T;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiguilleurMainFx extends Application {
	
	static {
		JjJdk.initializer().executeBlocking();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		T.trace(this);

		// TODO: enregistrer la fenêtre JavaFx

		AiguilleurMain.main();
	}
}
