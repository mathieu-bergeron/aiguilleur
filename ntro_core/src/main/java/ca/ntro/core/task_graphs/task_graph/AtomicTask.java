package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface AtomicTask<AT extends  AtomicTask<AT,IAT>,
                            IAT extends ImmutableAtomicTask<IAT>>

       extends   ImmutableAtomicTask<IAT> {

	<R> void registerResult(Result<R> result);
	
	IAT toImmutableAtomicTask();

}
