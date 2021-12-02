package ca.ntro.core.task_graphs.task_graph;

public interface TaskReducer<T  extends Task<T,AT>, 
                             AT extends AtomicTask<T,AT>,
                             R extends Object> {
	
	R reduceTask(R accumulator, T task) throws Throwable;

}
