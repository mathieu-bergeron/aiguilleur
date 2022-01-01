package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericAtomicTaskTrace;

public interface GenericAtomicTask<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>> 

       extends AtomicTaskNotifyer, 
               TaskStateAccessor {
	
	AtomicTaskId id();
	T parentTask();
	
	GenericAtomicTaskTrace newTrace();

}
