package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.task_graph.ImmutableTask;
import ca.ntro.core.task_graphs.task_graph.TaskId;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, ImmutableTask subTask);

}
