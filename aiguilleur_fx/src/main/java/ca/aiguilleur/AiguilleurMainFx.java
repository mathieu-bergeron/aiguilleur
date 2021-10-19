package ca.aiguilleur;

import ca.jj.core.Jj;
import ca.jj.core.JjJdk;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiguilleurMainFx extends Application {
	
	static {
		// initializator est un graphe
		// de tâche. En JS, le graphe 
		// est capable de gérer p.ex. le chargement
		// de fichier .js externe (services)
		// et le chargement de fichier map
		JjJdk.initializator().executeBlocking();

		// Jj prêt à utiliser
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Jj.trace(this);
		
		// TODO: enregistrer la fenêtre JavaFx

		AiguilleurMain.main();
	}
}
