package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.task_graph.AtomicTask;

public interface AtomicTaskTrace extends GenericTrace<Object> {
	
	AtomicTask<?,?> task();
	
}
