package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.result.Result;

public interface TaskGraph extends GenericTaskGraph<Task, AtomicTask> {

	Future<ObjectMap> execute();
	Future<ObjectMap> execute(TaskGraphOptions options);

	Result<ObjectMap> executeBlocking();
	Result<ObjectMap> executeBlocking(TaskGraphOptions options);

	// JSweet: name clash with TaskGraph.newGraph
	public static TaskGraph newGraph() {
		
		TaskGraphNtro graph = new TaskGraphNtro();

		graph.setTaskClass(TaskNtro.class);
		graph.setDefaultAtomicTaskClass(AtomicTaskMessageHandler.class);

		graph.initialize();

		return graph;
	}

}
