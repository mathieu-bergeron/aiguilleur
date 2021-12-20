package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public interface ResultsReader {

	boolean hasNext();
	ObjectMap next();

}
