package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public class TaskGraphNtro<T   extends Task<T,IT,AT,IAT>, 
					       IT  extends ImmutableTask<IT,AT,IAT>,
                           AT  extends AtomicTask<AT,IAT>,
                           IAT extends ImmutableAtomicTask<IAT>,
                           IG  extends ImmutableTaskGraph<T,IT,AT,IAT,IG>,
                           G   extends TaskGraph<T,IT,AT,IAT,IG,G>> 

	   implements TaskGraph<T,IT,AT,IAT,IG,G> {
	
	
	private HierarchicalDagBuilder<TaskGraphNode<T,IT,AT,IAT,IG,G>,
	                               TaskGraphEdge<T,IT,AT,IAT,IG,G>,
	                               HierarchicalDagSearchOptions,
	                               HierarchicalDag<TaskGraphNode<T,IT,AT,IAT,IG,G>,
	                                               TaskGraphEdge<T,IT,AT,IAT,IG,G>,
	                                               HierarchicalDagSearchOptions>>     hdag = new HierarchicalDagBuilderNtro<>();

	@Override
	public T findTask(TaskId id) {
		return hdag.findNode(id).task();
	}

	@Override
	public G addTask(T task) {
		hdag.addNode(new TaskGraphNodeNtro<T,IT,AT,IAT,IG,G>(task));

		return (G) this;
	}

	@Override
	public IG toImmutableGraph() {
		return (IG) this;
	}
}
