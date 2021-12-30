package ca.ntro.core.task_graphs.task_graph;


public class ExecutableTaskGraphOptionsDefault implements ExecutableTaskGraphOptions {

	@Override
	public boolean shouldHalt(ExecutableTaskGraph graph) {
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
