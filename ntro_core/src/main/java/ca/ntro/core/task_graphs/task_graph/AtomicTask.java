package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends ResultsAccessor {
	
	AtomicTaskId id();
	T parentTask();

	void registerNewResult(Object value);
	void clearResults();

}
