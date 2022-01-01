package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskNotifyer;
import ca.ntro.core.values.ObjectMap;

public class CancelHandlerDefault implements CancelHandler {

	@Override
	public void cancel(ObjectMap currentResults, AtomicTaskNotifyer notifyer) throws Throwable {
		notifyer.notifyWaitingForResult();
	}
}
