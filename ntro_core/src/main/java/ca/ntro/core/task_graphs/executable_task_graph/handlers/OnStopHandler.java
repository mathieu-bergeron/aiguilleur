package ca.ntro.core.task_graphs.executable_task_graph.handlers;

import ca.ntro.core.values.ObjectMap;

public interface OnStopHandler {

	void stop(ObjectMap currentResults) throws Throwable;

}
