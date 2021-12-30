package ca.ntro.core.task_graphs.generic_task_graph;

public interface TaskStateAccessor {
	
	boolean isBlocked();
	boolean isInProgress();
	boolean isDone();

}
