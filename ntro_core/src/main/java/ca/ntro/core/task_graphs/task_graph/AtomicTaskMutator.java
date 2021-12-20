package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTaskMutator {

	void addResult(Object value);
	void clearResults();

	void notifyTaskBlocked();
	void notifyTaskInProgress();
	void notifyTaskDone();

}
