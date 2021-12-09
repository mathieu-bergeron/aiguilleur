package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;

public interface TaskGraph<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>> {
	
	void setGraphName(String graphName);

	T findTask(TaskId id);

	T addTask(TaskId id);
	T addTask(String id);
	
	void write(GraphWriter writer);

	static <T extends Task<T,AT>, AT extends AtomicTask<T,AT>> TaskGraph<T,AT> newGraph(Class<T> taskClass, Class<AT> atomicTaskClass) {
		
		TaskGraphNtro<T,AT> graph = new TaskGraphNtro<>();

		
		return graph;
	}
}
