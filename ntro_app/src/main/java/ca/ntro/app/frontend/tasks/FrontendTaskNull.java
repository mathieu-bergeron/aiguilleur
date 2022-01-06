package ca.ntro.app.frontend.tasks;

import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph.Task;

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
