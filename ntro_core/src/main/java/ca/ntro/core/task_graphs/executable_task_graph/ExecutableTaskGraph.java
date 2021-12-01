package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.result.Result;

public interface ExecutableTaskGraph {

	Future<ObjectMap> execute();
	Result<ObjectMap> executeBlocking();

}
