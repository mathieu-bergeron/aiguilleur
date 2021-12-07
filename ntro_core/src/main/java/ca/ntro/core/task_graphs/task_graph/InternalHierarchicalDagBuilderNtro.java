package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

public class InternalHierarchicalDagBuilderNtro<T  extends Task<T,AT>, 
                                                AT extends AtomicTask<T,AT>>

       extends HierarchicalDagBuilderNtro<TaskGraphNode<T,AT>,
	                                      TaskGraphEdge<T,AT>,
	                                      TaskGraphSearchOptionsBuilder,
	                                      TaskGraphNodeBuilder<T,AT>,
	                                      HierarchicalDag<TaskGraphNode<T,AT>,
	                                                      TaskGraphEdge<T,AT>,
	                                                      TaskGraphSearchOptionsBuilder>>

	   implements InternalHierarchicalDagBuilder<T,AT> {

	@Override
	protected HierarchicalDag<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> newGraphInstance() {
		throw new RuntimeException("TODO");
	}

	@Override
	protected TaskGraphNodeBuilder<T, AT> newNodeBuilderInstance() {
		throw new RuntimeException("TODO");
	}



}
