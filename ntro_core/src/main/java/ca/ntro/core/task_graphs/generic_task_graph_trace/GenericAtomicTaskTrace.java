package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;

public interface GenericAtomicTaskTrace extends GenericTraceAccessor<Object> {
	
	GenericAtomicTask<?,?> task();
	
}