package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends ResultsAccessor, TaskStateAccessor, AtomicTaskMutator {
	
	AtomicTaskId id();
	T parentTask();

}
