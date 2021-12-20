package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.stream.Stream;

public interface Task<T  extends Task<T,AT>, 
                      AT extends AtomicTask<T,AT>> 

       extends ResultsAccessor, TaskStateAccessor {

	TaskId id();
	TaskGraph<T,AT> parentGraph();

	boolean hasParentTask();
	T parentTask();

	AT findEntryTask(AtomicTaskId id);
	AT findEntryTask(String id);

	AT findExitTask(AtomicTaskId id);
	AT findExitTask(String id);

	AT findAtomicTask(AtomicTaskId id);
	AT findAtomicTask(String id);
	
	T addSubTask(TaskId id);
	T addSubTask(String id);
	void addSubTask(T subTask);

	T addPreviousTask(TaskId id);
	T addPreviousTask(String id);
	void addPreviousTask(T previousTask);

	T addNextTask(TaskId id);
	T addNextTask(String id);
	void addNextTask(T nextTask);

	AT addEntryTask(AtomicTaskId id);
	AT addEntryTask(String id);
	void addEntryTask(AT entryTask);

	AT addExitTask(AtomicTaskId id);
	AT addExitTask(String id);
	void addExitTask(AT exitTask);

	void registerResultsLock(PreviousResultsAcceptor resultsLock);

	Stream<T>  previousTasks();
	Stream<AT> entryTasks();
	Stream<T>  subTasks();
	Stream<AT> exitTasks();
	Stream<T>  nextTasks();

	Stream<T> reachableTasks();
	Stream<T> reachableTasks(TaskGraphSearchOptions options);
}