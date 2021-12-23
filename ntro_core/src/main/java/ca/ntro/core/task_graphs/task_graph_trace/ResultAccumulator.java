package ca.ntro.core.task_graphs.task_graph_trace;

public interface ResultAccumulator 

       extends AtomicTaskTrace {

	void addResult(Object result);
	void clearResults();

}
