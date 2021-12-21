package ca.ntro.core.task_graphs.task_graph;

public interface ResultAccumulator extends ResultAccessor {

	void addResult(Object result);
	void clearResults();

	ResultIterator resultIterator();

}
