package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public interface ResultsIterator {

	boolean hasNextResults();
	ObjectMap nextResults();

}
