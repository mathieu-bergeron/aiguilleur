package ca.ntro.core.task_graphs.executable_task_graph.handlers;

public interface Notifyer {

	void addResult(Object value);
	void clearResults();

	void notifyThatNotWaitingForNewResults();

	void notifyOfNewResult(Object value);
	void notifyOfNoResult();

}
