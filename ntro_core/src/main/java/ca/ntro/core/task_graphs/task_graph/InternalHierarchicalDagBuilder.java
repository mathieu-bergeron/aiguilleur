package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptionsBuilder;

public interface InternalHierarchicalDagBuilder<T  extends Task<T,AT,TG>, 
                                                AT extends AtomicTask<T,AT,TG>,
                                                TG extends TaskGraph<T,AT>> 

	   extends HierarchicalDagBuilder<TaskGraphNode<T,AT,TG>,
	                                  TaskGraphEdge<T,AT,TG>,
	                                  TaskGraphSearchOptionsBuilder,
	                                  HierarchicalDag<TaskGraphNode<T,AT,TG>,
	                                                  TaskGraphEdge<T,AT,TG>,
	                                                  TaskGraphSearchOptionsBuilder>> {
	
}
