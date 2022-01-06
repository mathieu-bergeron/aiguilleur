package ca.ntro.app.frontend;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskGraphOptions;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public class FrontendTaskGraphOptions implements TaskGraphOptions {

	@Override
	public boolean shouldHalt(GenericTaskGraph<?, ?> graph, TaskGraphTrace trace) {
		return false;
	}

	@Override
	public boolean shouldWriteGraph() {
		return true;
	}

	@Override
	public long maxDelayMillis() {
		return 0;
	}

}
