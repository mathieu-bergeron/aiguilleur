package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public interface TaskStateAccessor {
	
	boolean isBlocked(TaskTrace trace);
	boolean isInProgress(TaskTrace trace);
	boolean isDone(TaskTrace trace);

}
