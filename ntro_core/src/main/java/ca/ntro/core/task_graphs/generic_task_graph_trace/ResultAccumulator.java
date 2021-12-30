package ca.ntro.core.task_graphs.generic_task_graph_trace;

public interface ResultAccumulator 

       extends AtomicTaskTrace {

	void addResult(Object result);
	void clearResults();

}
