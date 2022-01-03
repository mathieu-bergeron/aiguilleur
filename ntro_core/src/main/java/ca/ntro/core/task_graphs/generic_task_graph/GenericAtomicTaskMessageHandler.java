package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceMessageHandler;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;

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
	protected AtomicTaskTrace newTraceInstance(TaskTraceNtro parentTrace) {
		return new AtomicTaskTraceMessageHandler(this, parentTrace);
	}
}
