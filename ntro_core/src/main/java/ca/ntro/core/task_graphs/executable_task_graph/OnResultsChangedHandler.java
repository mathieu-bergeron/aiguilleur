package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public interface OnResultsChangedHandler {
	
	void start(ObjectMap currentResults, AtomicTaskMutator notifyer) throws Throwable;

}
