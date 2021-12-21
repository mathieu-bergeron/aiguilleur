package ca.ntro.core.task_graphs.task_graph;


public interface ResultIterator {

	boolean hasNextResult();
	Object nextResult();

}
