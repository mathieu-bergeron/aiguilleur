package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;

public interface GenericAtomicTask<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>> 

       extends   AtomicTaskMutator {
	
	AtomicTaskId id();
	T parentTask();
	
	AtomicTaskTrace newTrace();

}
