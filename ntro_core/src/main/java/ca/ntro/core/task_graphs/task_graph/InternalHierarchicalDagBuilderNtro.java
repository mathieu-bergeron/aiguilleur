package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

public class InternalHierarchicalDagBuilderNtro<T  extends Task<T,AT,TG>, 
                                                AT extends AtomicTask<T,AT,TG>,
                                                TG extends TaskGraph<T,AT>> 

       extends HierarchicalDagBuilderNtro<TaskGraphNode<T,AT,TG>,
	                                      TaskGraphEdge<T,AT,TG>,
	                                      TaskGraphSearchOptionsBuilder,
	                                      HierarchicalDag<TaskGraphNode<T,AT,TG>,
	                                                      TaskGraphEdge<T,AT,TG>,
	                                                      TaskGraphSearchOptionsBuilder>>

	   implements InternalHierarchicalDagBuilder<T,AT,TG> {

	public InternalHierarchicalDagBuilderNtro() {
		super();
	}

	public InternalHierarchicalDagBuilderNtro(String graphName) {
		super(graphName);
	}
}
