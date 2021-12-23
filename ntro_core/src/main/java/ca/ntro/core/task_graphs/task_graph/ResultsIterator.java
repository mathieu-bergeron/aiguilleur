package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public interface ResultsIterator {
	

	boolean hasResults();
	ObjectMap results();

	// XXX: wether we are waiting for
	//      results or not depends on 
	//      the state of the AtomicTask iterators
	boolean isWaitingForResults();

	boolean hasNextResults();
	void advanceToNextResults();

}
