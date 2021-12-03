package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilderNtro;

public class TaskGraphNodeBuilderNtro<T  extends Task<T,AT>, 
                                      AT extends AtomicTask<T,AT>>

      extends HierarchicalNodeBuilderNtro<TaskGraphNode<T,AT>,
                                          TaskGraphEdge<T,AT>,
                                          TaskGraphSearchOptionsBuilder>

	  implements TaskGraphNodeBuilder<T,AT> {

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

	@Override
	public GenericGraph<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}
}
