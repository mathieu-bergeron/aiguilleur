package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.Notifyer;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends ResultsAccessor, TaskStateAccessor, Notifyer {
	
	AtomicTaskId id();
	T parentTask();

}
