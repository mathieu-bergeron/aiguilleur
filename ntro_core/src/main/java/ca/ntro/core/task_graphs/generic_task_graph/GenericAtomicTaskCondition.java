package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceCondition;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;

public class GenericAtomicTaskCondition<T extends GenericTask<T,AT>, 
                                        AT extends GenericAtomicTask<T,AT>>

       extends GenericAtomicTaskNtro<T,AT> {

	@Override
	protected AtomicTaskTrace newTraceInstance(TaskTraceNtro parentTrace) {
		return new AtomicTaskTraceCondition(this, parentTrace);
	}

}
