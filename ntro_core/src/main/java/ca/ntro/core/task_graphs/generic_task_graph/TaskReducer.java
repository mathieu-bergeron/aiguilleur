package ca.ntro.core.task_graphs.generic_task_graph;

public interface TaskReducer<T  extends GenericTask<T,AT>, 
                             AT extends GenericAtomicTask<T,AT>,
                             R extends Object> {
	
	R reduceTask(R accumulator, T task) throws Throwable;

}
