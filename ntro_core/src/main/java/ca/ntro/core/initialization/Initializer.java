package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.task_graph.TaskGraph;

public interface Initializer extends TaskGraph {
	
	Initializer setOptions(InitializerOptions options);




}
