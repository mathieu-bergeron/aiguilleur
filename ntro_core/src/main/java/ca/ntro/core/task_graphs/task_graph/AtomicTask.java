package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface AtomicTask<T  extends Task<T,AT>, 
                            AT extends AtomicTask<T,AT>> 

       extends TaskStateAccessor {
	
	AtomicTaskId id();
	T parentTask();

	<R> void registerResult(Result<R> result);

	<R> Result<R> result();
}
