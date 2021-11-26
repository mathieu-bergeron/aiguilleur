package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.NodeValue;

public interface Task<AT extends AtomicTask> extends NodeValue {
	
	TaskState state();
	

	AT findEntryTask(AtomicTaskId id);

	AT findExitTask(AtomicTaskId id);
	
	

}
