package ca.ntro.core.task_graphs.generic_task_graph;

public interface AtomicTaskMutator {

	void addResult(Object value);
	void clearResults();

	void notifyWaitingForResult();

}
