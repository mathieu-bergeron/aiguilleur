package ca.ntro.jj.tasks.generic;

import ca.ntro.jj.core.identifyers.TaskId;

public interface GenericTaskGraph<GT extends GenericTask<?,GAT>, GAT extends GenericAtomicTask> {

	GT getTaskById(TaskId taskId);

	void addTask(TaskId taskId, GT task);

}
