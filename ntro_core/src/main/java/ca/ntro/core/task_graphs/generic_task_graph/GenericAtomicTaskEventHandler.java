package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceEventHandler;

public class GenericAtomicTaskEventHandler<T extends GenericTask<T,AT>, 
                                             AT extends GenericAtomicTask<T,AT>>

       extends GenericAtomicTaskNtro<T,AT> {

	@Override
	public AtomicTaskTrace newTrace() {
		return new AtomicTaskTraceEventHandler(this);
	}
}
