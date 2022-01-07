package ca.ntro.core.task_graphs.handlers;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public interface CancelHandler {
	
	void cancel(ObjectMap currentResults, AtomicTaskMutator notifyer) throws Throwable;

}
