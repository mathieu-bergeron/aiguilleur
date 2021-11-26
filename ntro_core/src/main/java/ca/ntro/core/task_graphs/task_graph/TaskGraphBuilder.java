package ca.ntro.core.task_graphs.task_graph;

public interface TaskGraphBuilder<A extends Task<AT>, AT extends AtomicTask, TG extends TaskGraph<A,AT>> {

	TaskBuilder<A,AT> addTask(String taskName);

	TG toTaskGraph();

}
