package ca.ntro.core.task_graphs.task_graph;

public interface TaskGraphBuilder<T extends Task, AT extends AtomicTask> {

	public static <T extends Task, AT extends AtomicTask> TaskGraphBuilder<T,AT> newBuilder(){
		return new TaskGraphNtro<T,AT>();
	}

	public static <T extends Task, AT extends AtomicTask> TaskGraphBuilder<T,AT> newBuilder(String graphName){
		return new TaskGraphNtro<T,AT>(graphName);
	}

}
