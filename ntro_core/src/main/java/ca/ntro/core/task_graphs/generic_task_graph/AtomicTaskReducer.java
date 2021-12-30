package ca.ntro.core.task_graphs.generic_task_graph;

public interface AtomicTaskReducer<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>,
                                   R extends Object> {

	R reduceAtomicTask(R accumulator, AT atomicTask) throws Throwable;

}
