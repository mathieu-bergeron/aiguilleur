package ca.ntro.jj.init;

import ca.ntro.jj.task_graph.TaskGraph;

public interface Initializer extends TaskGraph {
	
	Initializer setOptions(InitializerOptions options);


}
