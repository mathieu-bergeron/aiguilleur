package ca.ntro.jj.tasks.task_graph;

import ca.ntro.jj.tasks.meta.AtomicTaskMeta;
import ca.ntro.jj.tasks.meta.TaskMeta;

public interface TaskGraph<T extends TaskMeta<T,AT>, AT extends AtomicTaskMeta> {

	T getTaskById(String taskId);

	void addTask(String taskId, T task);

}
