package ca.aiguilleur;

import ca.jj.core.Jj;
import ca.jj.core.JjJdk;
import ca.jj.demo.DemoMain;

public class DemoJdk {
	
	static {
		// initializator est un graphe
		// de tâche. En JS, le graphe 
		// est capable de gérer p.ex. le chargement
		// de fichier .js externe (services)
		// et le chargement de fichier map
		JjJdk.initializator().executeBlocking();

		// Jj prêt à utiliser
	}

	public static void main(String[] args) {
		Jj.trace(DemoJdk.class);
		
		DemoMain.main();
	}
}
