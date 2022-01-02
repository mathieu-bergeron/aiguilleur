package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceMessageHandler;

public class GenericAtomicTaskMessageHandler<T extends GenericTask<T,AT>, 
                                             AT extends GenericAtomicTask<T,AT>>

       extends GenericAtomicTaskNtro<T,AT> {

	public GenericAtomicTaskMessageHandler() {
		super();
	}

	public GenericAtomicTaskMessageHandler(String atomicTaskId) {
		super(atomicTaskId);
	}

	@Override
	public AtomicTaskTrace newTrace() {
		return new AtomicTaskTraceMessageHandler(this);
	}
}
