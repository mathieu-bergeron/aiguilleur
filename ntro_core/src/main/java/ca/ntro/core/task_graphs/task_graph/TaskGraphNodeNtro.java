package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilderNtro;

public class TaskGraphNodeNtro<T  extends Task<T,AT,TG>, 
                               AT extends AtomicTask<T,AT,TG>,
                               TG extends TaskGraph<T,AT,TG>> 

      extends HierarchicalNodeBuilderNtro<TaskGraphNode<T,AT,TG>,
                                          TaskGraphEdge<T,AT,TG>,
                                          HierarchicalDagSearchOptions>

	  implements TaskGraphNode<T,AT,TG> {

	private T task;

	public T getTask() {
		return task;
	}

	public void setTask(T task) {
		this.task = task;
	}

	public TaskGraphNodeNtro(T task) {
		super(task.id());
		setTask(task);
	}

	@Override
	public T task() {
		return getTask();
	}
}
