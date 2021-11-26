package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskId;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, Task subTask);

}
