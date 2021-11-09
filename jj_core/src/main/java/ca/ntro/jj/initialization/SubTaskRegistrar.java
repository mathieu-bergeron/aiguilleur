package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.TaskId;
import ca.ntro.jj.tasks.base.Task;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, Task subTask);

}
