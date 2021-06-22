package ca.ntro.jj.tasks.task_graph;

import ca.ntro.jj.common.identifyiers.TaskId;
import ca.ntro.jj.tasks.meta.AtomicTaskMeta;
import ca.ntro.jj.tasks.meta.TaskMeta;

public interface TaskGraphMeta<TM extends TaskMeta<?,ATM>, ATM extends AtomicTaskMeta> {

	TM getTaskById(TaskId taskId);

	void addTask(TaskId taskId, TM task);

}
