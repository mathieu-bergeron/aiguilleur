package ca.ntro.core.tasks.generic;

import ca.ntro.core.identifyers.TaskId;

public interface GenericTaskGraph<GT extends GenericTask<?,GAT>, GAT extends GenericAtomicTask> {

	GT getTaskById(TaskId taskId);

	void addTask(TaskId taskId, GT task);

}
