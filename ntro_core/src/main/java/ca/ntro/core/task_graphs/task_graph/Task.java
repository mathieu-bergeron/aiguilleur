package ca.ntro.core.task_graphs.task_graph;

public interface Task<T  extends Task<T,AT,TG>, 
                      AT extends AtomicTask<T,AT,TG>,
                      TG extends TaskGraph<T,AT,TG>> {

	TaskId id();
	TG parentGraph();

	boolean isQueued();
	boolean isInProgress();
	boolean isDone();

	AT findEntryTask(AtomicTaskId id);
	AT findExitTask(AtomicTaskId id);
	
	T addSubTask(T subTask);
	T addPreviousTask(T previousTask);
	T addNextTask(T nextTask);
	T addEntryTask(AT entryTask);
	T addExitTask(AT exitTask);
}