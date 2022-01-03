package ca.ntro.core.task_graphs.generic_task_graph.handlers;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public interface ExecuteHandler {
	
	void execute(ObjectMap currentResults, AtomicTaskMutator notifyer) throws Throwable;

}
