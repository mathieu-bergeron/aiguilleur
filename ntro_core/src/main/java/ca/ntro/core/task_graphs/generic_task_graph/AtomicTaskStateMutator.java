package ca.ntro.core.task_graphs.generic_task_graph;

public interface AtomicTaskStateMutator {

	void addResult(Object value);
	void clearResults();

	void notifyWaitingForResult();

}
