package ca.jj.core;

import ca.ntro.jj.wrappers.future.Future;

public class JjJdk {
	
	private static Object createInitializationGraph() {

		// initializator est un graphe
		// de tâche. En JS, le graphe 
		// est capable de gérer p.ex. le chargement
		// de fichier .js externe (services)
		// et le chargement de fichier map
		
		return null;
	}

	public static Future<Void> initialize() {
		
		Object initializationGraph = createInitializationGraph();
		
		return initializationGraph.execute();
	}

	public static void initializeBlocking() {
		
		Object initializationGraph = createInitializationGraph();
		
		initializationGraph.executeBlocking();
		
		
	}

}
