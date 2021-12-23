package ca.ntro.core.task_graphs.task_graph;


public interface ResultIterator {
	
	boolean hasResult();
	Object result();

	boolean hasNextResult();
	void    advanceToNextResult();

}
