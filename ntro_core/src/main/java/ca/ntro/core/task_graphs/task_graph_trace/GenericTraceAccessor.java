package ca.ntro.core.task_graphs.task_graph_trace;

public interface GenericTraceAccessor<V extends Object> extends GenericTraceNexter {
	
	boolean hasCurrent();
	V current();
	
	boolean isWaiting();

}
