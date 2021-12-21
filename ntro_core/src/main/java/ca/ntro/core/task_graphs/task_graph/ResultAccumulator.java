package ca.ntro.core.task_graphs.task_graph;

public interface ResultAccumulator {

	void pushResult(Object result);

	ResultsIterator resultsIterator();

}
