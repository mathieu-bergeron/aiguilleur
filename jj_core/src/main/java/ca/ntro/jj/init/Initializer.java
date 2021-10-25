package ca.ntro.jj.init;

import ca.jj.core.task_graph.TaskGraph;

public interface Initializer extends TaskGraph {
	
	Initializer setOptions(InitializerOptions options);


}
