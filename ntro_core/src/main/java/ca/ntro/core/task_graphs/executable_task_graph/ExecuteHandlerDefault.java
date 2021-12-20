package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTaskNotifyer;
import ca.ntro.core.values.ObjectMap;

public class ExecuteHandlerDefault implements ExecuteHandler {

	@Override
	public void execute(ObjectMap currentResults, AtomicTaskNotifyer notifyer) {
		notifyer.notifyTaskInProgress();
	}

}
