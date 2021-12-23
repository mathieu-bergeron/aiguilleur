package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.values.ObjectMap;

public interface ResultsAccumulator {

	void pushResults(ObjectMap newResults);

	TaskTrace resultsIterator();

}
