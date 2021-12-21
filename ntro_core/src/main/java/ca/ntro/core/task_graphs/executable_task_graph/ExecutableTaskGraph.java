package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.result.Result;

public interface ExecutableTaskGraph extends TaskGraph<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {

	Future<ObjectMap> execute();
	Future<ObjectMap> execute(boolean writeGraph);

	Result<ObjectMap> executeBlocking();
	Result<ObjectMap> executeBlocking(long maxDelayMillis);
	Result<ObjectMap> executeBlocking(long maxDelayMillis, boolean writeGraph);

	// JSweet: name clash with TaskGraph.newGraph
	public static ExecutableTaskGraph newExecutableGraph() {
		
		ExecutableTaskGraphNtro graph = new ExecutableTaskGraphNtro();

		graph.setTaskClass(ExecutableTaskNtro.class);
		graph.setAtomicTaskClass(ExecutableAtomicTaskNtro.class);
		
		graph.initialize();

		return graph;
	}

}
