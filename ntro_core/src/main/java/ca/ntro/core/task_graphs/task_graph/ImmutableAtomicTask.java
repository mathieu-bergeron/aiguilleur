package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface ImmutableAtomicTask<IAT extends ImmutableAtomicTask<IAT>> {

	<R> Result<R> result();

}
