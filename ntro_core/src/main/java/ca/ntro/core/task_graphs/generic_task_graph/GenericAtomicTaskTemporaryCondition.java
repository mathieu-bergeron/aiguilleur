package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericAtomicTaskTrace;
import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericAtomicTaskTraceTemporaryCondition;

public class GenericAtomicTaskTemporaryCondition<T extends GenericTask<T,AT>, 
                                             AT extends GenericAtomicTask<T,AT>>

       extends GenericAtomicTaskNtro<T,AT> {

	@Override
	public GenericAtomicTaskTrace newTrace() {
		return new GenericAtomicTaskTraceTemporaryCondition(this);
	}
}
