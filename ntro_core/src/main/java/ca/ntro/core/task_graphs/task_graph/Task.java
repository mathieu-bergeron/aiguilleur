package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface Task<T  extends Task<T,AT>, 
                      AT extends AtomicTask<T,AT>> {

	TaskId id();
	TaskGraph<T,AT> parentGraph();

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
	
	boolean ifAllPreviousTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomePreviousTaskMatches(TaskMatcher<T,AT> matcher);
	void forEachPreviousTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reducePreviousTasks(R initialValue, TaskReducer<T,AT,R> reducer);

	boolean ifAllEntryTasksMatch(AtomicTaskMatcher<T,AT> matcher);
	boolean ifSomeEntryTaskMatches(AtomicTaskMatcher<T,AT> matcher);
	void forEachEntryTask(AtomicTaskVisitor<T,AT> visitor);
	<R> Result<R> reduceEntryTasks(R initialValue, AtomicTaskReducer<T,AT,R> reducer);

	boolean ifAllSubTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomeSubTaskMatches(TaskMatcher<T,AT> matcher);
	void forEachSubTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceSubTasks(R initialValue, TaskReducer<T,AT,R> reducer);

	boolean ifAllExitTasksMatch(AtomicTaskMatcher<T,AT> matcher);
	boolean ifSomeExitTaskMatches(AtomicTaskMatcher<T,AT> matcher);
	void forEachExitTask(AtomicTaskVisitor<T,AT> visitor);
	<R> Result<R> reduceExitTasks(R initialValue, AtomicTaskReducer<T,AT,R> reducer);

	boolean ifAllNextTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomeNextTaskMatches(TaskMatcher<T,AT> matcher);
	void forEachNextTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceNextTasks(R initialValue, TaskReducer<T,AT,R> reducer);
	
}