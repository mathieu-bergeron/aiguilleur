package ca.ntro.jj.tasks.generic;

import ca.ntro.jj.common.identifyiers.TaskId;

public interface GenericTaskGraph<GT extends GenericTask<?,GAT>, GAT extends GenericAtomicTask> {

	GT getTaskById(TaskId taskId);

	void addTask(TaskId taskId, GT task);

}
