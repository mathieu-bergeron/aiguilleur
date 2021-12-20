package ca.ntro.core.task_graphs.task_graph;

public interface TaskStateAccessor {

	boolean isWaiting();
	boolean isRunning();
	boolean isDone();

}
