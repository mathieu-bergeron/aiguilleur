package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public interface TaskGraph<T  extends Task<T,AT,TG>, 
                           AT extends AtomicTask<T,AT,TG>,
                           TG extends TaskGraph<T,AT,TG>> {

	T findTask(TaskId id);
	
	T createTask(TaskId id);

	TG addTask(T task);
	
	void write(GraphWriter writer);
}
