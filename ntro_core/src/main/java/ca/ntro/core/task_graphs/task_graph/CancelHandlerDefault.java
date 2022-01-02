package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public class CancelHandlerDefault implements CancelHandler {

	@Override
	public void cancel(ObjectMap currentResults, AtomicTaskMutator notifyer) throws Throwable {
		notifyer.notifyWaitingForResult();
	}
}
