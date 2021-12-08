package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;

public interface TaskGraph<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>> {

	T findTask(TaskId id);
	
	T createTask(String id);
	T createTask(TaskId id);

	void addTask(T task);
	
	void write(GraphWriter writer);
}
