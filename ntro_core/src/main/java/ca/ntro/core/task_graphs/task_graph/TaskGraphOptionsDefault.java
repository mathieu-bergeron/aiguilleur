package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericTaskGraphTrace;

public class TaskGraphOptionsDefault implements TaskGraphOptions {

	@Override
	public boolean shouldHalt(GenericTaskGraph<?,?> graph, GenericTaskGraphTrace trace) {
		return graph.tasks().ifNone(task -> task.isInProgress(trace));
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
