package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTaskReducer<T  extends Task<T,AT,TG>, 
                                   AT extends AtomicTask<T,AT,TG>,
                                   TG extends TaskGraph<T,AT>,
                                   R extends Object> {

	R reduceAtomicTask(R accumulator, AT atomicTask) throws Throwable;

}
