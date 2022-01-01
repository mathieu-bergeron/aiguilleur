package ca.ntro.core.task_graphs.task_graph;


public class TaskGraphOptionsDefault implements TaskGraphOptions {

	@Override
	public boolean shouldHalt(TaskGraph graph) {
		return graph.tasks().ifNone(task -> task.isInProgress());
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
