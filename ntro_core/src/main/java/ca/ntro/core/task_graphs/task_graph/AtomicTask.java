package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends TaskStateAccessor, 
               ResultAccessor, 
               ResultIterator, 
               AtomicTaskNotifyer {
	
	AtomicTaskId id();
	T parentTask();
	
	boolean isBlocked();

}
