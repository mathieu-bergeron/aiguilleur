package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public class InternalHierarchicalDagBuilderNtro<T  extends Task<T,AT,TG>, 
                                                AT extends AtomicTask<T,AT,TG>,
                                                TG extends TaskGraph<T,AT,TG>> 

       extends HierarchicalDagBuilderNtro<TaskGraphNode<T,AT,TG>,
	                                      TaskGraphEdge<T,AT,TG>,
	                                      HierarchicalDagSearchOptions,
	                                      HierarchicalDag<TaskGraphNode<T,AT,TG>,
	                                                      TaskGraphEdge<T,AT,TG>,
	                                                      HierarchicalDagSearchOptions>>

	   implements InternalHierarchicalDagBuilder<T,AT,TG> {


}
