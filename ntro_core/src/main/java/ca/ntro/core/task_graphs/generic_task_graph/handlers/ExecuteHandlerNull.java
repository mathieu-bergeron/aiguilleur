package ca.ntro.core.task_graphs.generic_task_graph.handlers;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public class ExecuteHandlerNull implements ExecuteHandler {

	@Override
	public void execute(ObjectMap currentResults, AtomicTaskMutator notifyer) {
	}

}
