package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericTaskGraphTrace;

public interface TaskStateAccessor {
	
	boolean isBlocked(GenericTaskGraphTrace trace);
	boolean isInProgress(GenericTaskGraphTrace trace);
	boolean isDone(GenericTaskGraphTrace trace);

}
