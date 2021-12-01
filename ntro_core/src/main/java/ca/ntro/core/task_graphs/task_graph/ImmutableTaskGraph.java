package ca.ntro.core.task_graphs.task_graph;


public interface ImmutableTaskGraph<T   extends Task<T,IT,AT,IAT>, 
					                IT  extends ImmutableTask<IT,AT,IAT>,
                                    AT  extends AtomicTask<AT,IAT>,
                                    IAT extends ImmutableAtomicTask<IAT>,
                                    IG  extends ImmutableTaskGraph<T,IT,AT,IAT,IG>> {

	T findTask(TaskId id);

}
