package ca.ntro.core.task_graphs.task_graph;

public interface ResultAccumulator 

       extends ResultAccessor,
               ResultIterator {

	void addResult(Object result);
	void clearResults();

}
