package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericTaskTrace;

public interface GenericTask<T  extends GenericTask<T,AT>, 
                      AT extends GenericAtomicTask<T,AT>> 

       extends TaskStateAccessor {

	TaskId id();
	GenericTaskGraph<T,AT> parentGraph();

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
	AT addEntryTask(AtomicTaskId id, Class<? extends AT> atomicTaskClass);
	AT addEntryTask(String id, Class<? extends AT> atomicTaskClass);
	void addEntryTask(AT entryTask);

	AT addExitTask(AtomicTaskId id);
	AT addExitTask(String id);
	AT addExitTask(AtomicTaskId id, Class<? extends AT> atomicTaskClass);
	AT addExitTask(String id, Class<? extends AT> atomicTaskClass);
	void addExitTask(AT exitTask);

	Stream<T>  previousTasks();
	Stream<AT> entryTasks();
	Stream<T>  subTasks();
	Stream<AT> exitTasks();
	Stream<T>  nextTasks();

	Stream<T> reachableTasks();
	Stream<T> reachableTasks(TaskGraphSearchOptions options);

	GenericTaskTrace newTrace();
}