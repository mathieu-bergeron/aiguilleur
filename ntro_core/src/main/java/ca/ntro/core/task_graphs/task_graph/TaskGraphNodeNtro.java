package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilderNtro;

public class TaskGraphNodeNtro<T   extends Task<T,IT,AT,IAT>, 
					           IT  extends ImmutableTask<IT,AT,IAT>,
                               AT  extends AtomicTask<AT,IAT>,
                               IAT extends ImmutableAtomicTask<IAT>,
                               IG  extends ImmutableTaskGraph<T,IT,AT,IAT,IG>,
                               G   extends TaskGraph<T,IT,AT,IAT,IG,G>> 

      extends HierarchicalNodeBuilderNtro<TaskGraphNode<T,IT,AT,IAT,IG,G>,
                                          TaskGraphEdge<T,IT,AT,IAT,IG,G>,
                                          HierarchicalDagSearchOptions>

	  implements TaskGraphNode<T,IT,AT,IAT,IG,G> {

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
