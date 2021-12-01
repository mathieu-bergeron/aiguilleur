package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface AtomicTask<T  extends Task<T,AT,TG>, 
                            AT extends AtomicTask<T,AT,TG>,
                            TG extends TaskGraph<T,AT,TG>> {

	T parentTask();

	<R> void registerResult(Result<R> result);

	<R> Result<R> result();

}
