package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphSearchOptions;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public interface GenericTTask<T  extends GenericTTask<T,ST,ET,TG,G>,
                              ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                              ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                              TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                              G  extends GenericTTaskGraph<T,ST,ET,TG,G>> {

	TaskId id();
	G parentGraph();

	boolean hasParentTask();
	T parentTask();
	
	boolean isTaskGroup();
	boolean isSimpleTask();
	TG asTaskGroup();
	ST asSimpleTask();

	T addPreviousTask(TaskId id);
	T addPreviousTask(String id);
	void addPreviousTask(T previousTask);

	T addNextTask(TaskId id);
	T addNextTask(String id);
	void addNextTask(T nextTask);

	Stream<T>  previousTasks();
	Stream<T>  nextTasks();

	Stream<T> reachableTasks();
	Stream<T> reachableTasks(TaskGraphSearchOptions options);

}