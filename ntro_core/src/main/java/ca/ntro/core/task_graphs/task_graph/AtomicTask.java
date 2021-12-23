package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends AtomicTaskNotifyer {
	
	AtomicTaskId id();
	T parentTask();
	
	boolean isBlocked();
	
	AtomicTaskTrace newTrace();

}
