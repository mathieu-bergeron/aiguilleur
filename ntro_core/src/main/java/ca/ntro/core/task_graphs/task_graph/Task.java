package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public interface Task<AT extends AtomicTask> extends Node<Task<AT>, TaskGraphEdge<AT>, SearchOptions> {
	
	TaskState state();

	AT findEntryTask(AtomicTaskId id);

	AT findExitTask(AtomicTaskId id);
	

}
