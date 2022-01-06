package ca.ntro.app.frontend.tasks;

import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph.Task;

public interface TypedFrontendTaskDescriptor<O extends Object> {
	
	TaskId id();

	void cancel();
	void destroy();
}
