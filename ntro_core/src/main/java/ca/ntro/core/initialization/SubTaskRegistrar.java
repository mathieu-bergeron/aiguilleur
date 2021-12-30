package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, GenericTask subTask);

}
