package ca.ntro.core.task_graphs.generic_task_graph_trace;

public interface GenericTraceMutator<V extends Object> {

	void addResult(V result);
	void clearResults();

	void notifyWaitingForResult();

	void notifyCurrentResultWasUsed();
	void notifyCurrentResultCouldNotBeUsed();

}
