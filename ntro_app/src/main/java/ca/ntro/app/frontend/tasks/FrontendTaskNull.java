package ca.ntro.app.frontend.tasks;

import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public class FrontendTaskNull implements TypedFrontendTaskDescriptor<Void> {

	@Override
	public void destroy() {
	}

	@Override
	public void cancel() {
	}

	@Override
	public TaskId id() {
		return null;
	}

}
