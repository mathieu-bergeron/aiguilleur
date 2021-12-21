package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTaskNotifyer {

	void addResult(Object value);
	void clearResults();

	void notifyTaskBlocked();

}
