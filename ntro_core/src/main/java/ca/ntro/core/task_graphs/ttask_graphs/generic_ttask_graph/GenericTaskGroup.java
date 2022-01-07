package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public interface GenericTaskGroup<T  extends GenericTTask<T,ST,ET,TG,G>,
                                  ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                  ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                  TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                  G  extends GenericTTaskGraph<T,ST,ET,TG,G>> {

	ST addSimpleTask(TaskId id);
	ST addSimpleTask(String id);
	ST addSimpleTask(TaskId id, Class<? extends ST> taskClass);
	ST addSeimplTask(String id, Class<? extends ST> taskClass);

	TG addTaskGroup(TaskId id);
	TG addTaskGroup(String id);
	TG addTaskGroup(TaskId id, Class<? extends TG> taskGroupClass);
	TG addTaskGroup(String id, Class<? extends TG> taskGroupClass);

	Stream<T> startTasks();
	Stream<T> tasks();

}
