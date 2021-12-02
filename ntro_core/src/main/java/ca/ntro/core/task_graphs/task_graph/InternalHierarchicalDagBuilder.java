package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptionsBuilder;

public interface InternalHierarchicalDagBuilder<T  extends Task<T,AT>, 
                                                AT extends AtomicTask<T,AT>>

	   extends HierarchicalDagBuilder<TaskGraphNode<T,AT>,
	                                  TaskGraphEdge<T,AT>,
	                                  TaskGraphSearchOptionsBuilder,
	                                  HierarchicalDag<TaskGraphNode<T,AT>,
	                                                  TaskGraphEdge<T,AT>,
	                                                  TaskGraphSearchOptionsBuilder>> {
	
}
