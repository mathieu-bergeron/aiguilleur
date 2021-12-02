package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public interface TaskGraph<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>> {

	T findTask(TaskId id);
	
	// JSweet error: "supplied parameters do not match any signature of call target"
	//T createTask(String id);

	T createTask(TaskId id);

	void addTask(T task);
	
	void write(GraphWriter writer);
}
