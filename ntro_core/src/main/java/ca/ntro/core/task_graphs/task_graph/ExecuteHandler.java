package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskStateMutator;
import ca.ntro.core.values.ObjectMap;

public interface ExecuteHandler {
	
	void execute(ObjectMap currentResults, AtomicTaskStateMutator notifyer) throws Throwable;

}
