package ca.ntro.core.task_graphs.executable_task_graph.handlers;

import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.result.Result;

public interface OnSuspendHandler {

	void suspend(Result<ObjectMap> currentResults);

}
