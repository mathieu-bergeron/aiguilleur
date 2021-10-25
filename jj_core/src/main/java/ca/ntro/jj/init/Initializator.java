package ca.ntro.jj.init;

import ca.jj.core.task_graph.TaskGraph;

public interface Initializator extends TaskGraph {
	
	Initializator setOptions(InitializationOptions options);


}
