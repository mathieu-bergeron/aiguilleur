package ca.ntro.core.task_graphs.task_graph;

public interface TaskReducer<T  extends Task<T,AT,TG>, 
                             AT extends AtomicTask<T,AT,TG>,
                             TG extends TaskGraph<T,AT,TG>,
                             R extends Object> {
	
	R reduceTask(R accumulator, T task) throws Throwable;

}
