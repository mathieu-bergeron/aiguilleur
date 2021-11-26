package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public interface TaskGraph<T extends Task<AT>, 
                           AT extends AtomicTask> {

	T findTask(TaskId id);

	void notifyAtomicTaskCompleted(AT atomicTask, ObjectMap results);

	boolean isAtomicTaskCompleted(AT atomicTask);
	

}
