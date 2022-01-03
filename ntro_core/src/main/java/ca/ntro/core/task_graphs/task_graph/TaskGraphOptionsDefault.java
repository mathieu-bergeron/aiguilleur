package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public class TaskGraphOptionsDefault implements TaskGraphOptions {

	@Override
	public boolean shouldHalt(GenericTaskGraph<?,?> graph, TaskGraphTrace trace) {
		return !trace.hasNext()
				&& trace.isWaiting();
	}

	@Override
	public boolean shouldWriteGraph() {
		return false;
	}

	@Override
	public long maxDelayMillis() {
		return 0;
	}
}
