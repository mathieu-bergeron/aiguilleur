package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Node;

public interface Task<AT extends AtomicTask> extends Node {
	
	TaskState state();
	

	AT findEntryTask(AtomicTaskId id);

	AT findExitTask(AtomicTaskId id);
	
	

}
