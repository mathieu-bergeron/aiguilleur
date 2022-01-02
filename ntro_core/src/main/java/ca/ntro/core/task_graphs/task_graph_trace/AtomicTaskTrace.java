package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;

public interface AtomicTaskTrace 

       extends   GenericTraceAccessor<Object>,
                 AtomicTaskTraceMutator {

	GenericAtomicTask<?,?> task();
	
}
