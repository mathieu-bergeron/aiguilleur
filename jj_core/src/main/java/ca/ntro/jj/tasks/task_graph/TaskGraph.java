package ca.ntro.jj.tasks.task_graph;

import ca.ntro.jj.tasks.meta.TaskMeta;

public interface TaskGraph<TM extends TaskMeta<TM,?>> {

	TM getTaskById(String taskId);

	void addTask(String taskId, TM task);

}
