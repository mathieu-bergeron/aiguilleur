package ca.jj.core;

import ca.jj.core.task_graph.TaskGraph;
import ca.ntro.jj.wrappers.future.Future;

public class JjJdk {
	
	private static TaskGraph createInitializationGraph() {

		// initializator est un graphe
		// de tâche. En JS, le graphe 
		// est capable de gérer p.ex. le chargement
		// de fichier .js externe (services)
		// et le chargement de fichier map
		
		return null;
	}

	public static Future<Void> initialize() {
		
		TaskGraph initializationGraph = createInitializationGraph();
		
		return initializationGraph.execute();
	}

	public static void initializeBlocking() {
		
		TaskGraph initializationGraph = createInitializationGraph();
		
		initializationGraph.executeBlocking();
	}

}
