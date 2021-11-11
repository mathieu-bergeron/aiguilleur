package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.TaskId;
import ca.ntro.core.tasks.base.Task;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, Task subTask);

}
