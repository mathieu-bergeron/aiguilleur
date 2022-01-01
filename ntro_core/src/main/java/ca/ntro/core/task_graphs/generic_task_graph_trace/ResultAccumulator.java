package ca.ntro.core.task_graphs.generic_task_graph_trace;

public interface ResultAccumulator 

       extends GenericAtomicTaskTrace {

	void addResult(Object result);
	void clearResults();

}
