package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.task_graph.ExecutableTaskGraph;

public interface Initializer extends ExecutableTaskGraph {
	
	Initializer setOptions(InitializerOptions options);




}
