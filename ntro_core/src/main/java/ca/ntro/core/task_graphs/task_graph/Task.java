package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

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
	
	void forEachPreviousTask(TaskVisitor<T,AT,TG> visitor);
	<R> Result<R> reducePreviousTasks(R initialValue, TaskReducer<T,AT,TG,R> reducer);

	void forEachEntryTask(AtomicTaskVisitor<T,AT,TG> visitor);
	<R> Result<R> reduceEntryTasks(R initialValue, AtomicTaskReducer<T,AT,TG,R> reducer);

	void forEachSubTask(TaskVisitor<T,AT,TG> visitor);
	<R> Result<R> reduceSubTasks(R initialValue, TaskReducer<T,AT,TG,R> reducer);

	void forEachExitTask(AtomicTaskVisitor<T,AT,TG> visitor);
	<R> Result<R> reduceExitTasks(R initialValue, AtomicTaskReducer<T,AT,TG,R> reducer);

	void forEachNextTask(TaskVisitor<T,AT,TG> visitor);
	<R> Result<R> reduceNextTasks(R initialValue, TaskReducer<T,AT,TG,R> reducer);
	
}