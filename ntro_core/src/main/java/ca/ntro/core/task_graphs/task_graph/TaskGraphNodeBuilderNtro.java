package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilderNtro;

public class TaskGraphNodeBuilderNtro<T  extends Task<T,AT,TG>, 
                                      AT extends AtomicTask<T,AT,TG>,
                                      TG extends TaskGraph<T,AT>> 

      extends HierarchicalNodeBuilderNtro<TaskGraphNode<T,AT,TG>,
                                          TaskGraphEdge<T,AT,TG>,
                                          TaskGraphSearchOptionsBuilder>

	  implements TaskGraphNodeBuilder<T,AT,TG> {

	private T task;

	public T getTask() {
		return task;
	}

	public void setTask(T task) {
		this.task = task;
	}

	public TaskGraphNodeBuilderNtro(TaskId id) {
		super(id);
	}

	@Override
	public T task() {
		return getTask();
	}
}
