package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public interface Task<T  extends Task<T,AT>, 
                      AT extends AtomicTask<T,AT>> 

       extends TaskStateAccessor {

	TaskId id();
	TaskGraph<T,AT> parentGraph();

	boolean hasParentTask();
	T parentTask();


	AT findEntryTask(AtomicTaskId id);
	AT findExitTask(AtomicTaskId id);
	
	T addSubTask(T subTask);
	T addPreviousTask(T previousTask);
	T addNextTask(T nextTask);
	T addEntryTask(AT entryTask);
	T addExitTask(AT exitTask);
	
	// FIXME: a generic functional "hub" with all map/reduce functions
	// FunctionalHub<TaskMatcher<T,AT>, TaskVisitor<T,AT>, TaskMapper<T,AT>, TaskReducer<T,AT> previousTasks()
	//
	// previousTasks().ifAll();
	// previousTasks().ifSome();
	// previousTasks().findFirst()
	// previousTasks().findAll()
	// previousTasks().forEach()
	// previousTasks().map()
	// previousTasks().reduce()
	
	boolean ifAllPreviousTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomePreviousTaskMatches(TaskMatcher<T,AT> matcher);
	T findFirstPreviousTaskThatMatches(TaskMatcher<T,AT> matcher);
	void forEachPreviousTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reducePreviousTasks(R initialValue, TaskReducer<T,AT,R> reducer);

	boolean ifAllEntryTasksMatch(AtomicTaskMatcher<T,AT> matcher);
	boolean ifSomeEntryTaskMatches(AtomicTaskMatcher<T,AT> matcher);
	AT findFirstEntryTaskThatMatches(AtomicTaskMatcher<T,AT> matcher);
	void forEachEntryTask(AtomicTaskVisitor<T,AT> visitor);
	<R> Result<R> reduceEntryTasks(R initialValue, AtomicTaskReducer<T,AT,R> reducer);

	boolean ifAllSubTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomeSubTaskMatches(TaskMatcher<T,AT> matcher);
	T findFirstSubTaskThatMatches(TaskMatcher<T,AT> matcher);
	void forEachSubTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceSubTasks(R initialValue, TaskReducer<T,AT,R> reducer);

	boolean ifAllExitTasksMatch(AtomicTaskMatcher<T,AT> matcher);
	boolean ifSomeExitTaskMatches(AtomicTaskMatcher<T,AT> matcher);
	AT findFirstExitTaskThatMatches(AtomicTaskMatcher<T,AT> matcher);
	void forEachExitTask(AtomicTaskVisitor<T,AT> visitor);
	<R> Result<R> reduceExitTasks(R initialValue, AtomicTaskReducer<T,AT,R> reducer);

	boolean ifAllNextTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomeNextTaskMatches(TaskMatcher<T,AT> matcher);
	T findFirstNextTaskThatMatches(TaskMatcher<T,AT> matcher);
	void forEachNextTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceNextTasks(R initialValue, TaskReducer<T,AT,R> reducer);
	
	
	boolean ifAllReachableTasksMatch(TaskMatcher<T,AT> matcher);
	boolean ifSomeReachableTaskMatches(TaskMatcher<T,AT> matcher);
	T findFirstReachableTaskThatMatches(TaskMatcher<T,AT> matcher);
	void forEachReachableTask(TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceReachableTasks(R initialValue, TaskReducer<T,AT,R> reducer);

	boolean ifAllReachableTasksMatch(TaskGraphSearchOptionsBuilder options, TaskMatcher<T,AT> matcher);
	boolean ifSomeReachableTaskMatches(TaskGraphSearchOptionsBuilder options, TaskMatcher<T,AT> matcher);
	T findFirstReachableTaskThatMatches(TaskGraphSearchOptionsBuilder options, TaskMatcher<T,AT> matcher);
	void forEachReachableTask(TaskGraphSearchOptionsBuilder options, TaskVisitor<T,AT> visitor);
	<R> Result<R> reduceReachableTasks(TaskGraphSearchOptionsBuilder options, R initialValue, TaskReducer<T,AT,R> reducer);
}