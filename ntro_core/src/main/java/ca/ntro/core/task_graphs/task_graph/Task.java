package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.stream.Stream;

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
	
	Stream<T>  previousTasks();
	Stream<AT> entryTasks();
	Stream<T>  subTasks();
	Stream<AT> exitTasks();
	Stream<T>  nextTasks();

	Stream<T> reachableTasks();
	Stream<T> reachableTasks(TaskGraphSearchOptionsBuilder options);
}