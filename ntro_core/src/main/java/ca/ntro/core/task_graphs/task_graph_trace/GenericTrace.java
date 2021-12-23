package ca.ntro.core.task_graphs.task_graph_trace;

public interface GenericTrace<V extends Object> {
	
	boolean hasCurrent();
	V current();
	
	boolean isWaiting();

	boolean hasNext();
	void advanceToNext();

}
