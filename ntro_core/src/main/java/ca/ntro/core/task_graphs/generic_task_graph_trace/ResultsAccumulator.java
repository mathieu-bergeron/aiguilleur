package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.values.ObjectMap;

public interface ResultsAccumulator {

	void pushResults(ObjectMap newResults);

	GenericTaskTrace resultsIterator();

}
