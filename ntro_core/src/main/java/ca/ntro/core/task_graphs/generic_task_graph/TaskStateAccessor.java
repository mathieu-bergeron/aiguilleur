package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public interface TaskStateAccessor {
	
	boolean isBlocked(TaskGraphTrace trace);
	boolean isInProgress(TaskGraphTrace trace);
	boolean isDone(TaskGraphTrace trace);

}
