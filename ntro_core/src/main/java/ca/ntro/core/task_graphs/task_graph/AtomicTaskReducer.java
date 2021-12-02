package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTaskReducer<T  extends Task<T,AT>, 
                                   AT extends AtomicTask<T,AT>,
                                   R extends Object> {

	R reduceAtomicTask(R accumulator, AT atomicTask) throws Throwable;

}
