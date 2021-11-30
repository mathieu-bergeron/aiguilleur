package ca.ntro.core.task_graphs.task_graph;


public interface Task<AT extends AtomicTask> {
	
	TaskState state();

	AT findEntryTask(AtomicTaskId id);

	AT findExitTask(AtomicTaskId id);
	

}
