package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriter;

public interface InternalTaskGraphWriter<T  extends Task<T,AT,TG>, 
                                         AT extends AtomicTask<T,AT,TG>,
                                         TG extends TaskGraph<T,AT,TG>> 

       extends InternalHierarchicalDagWriter<TaskGraphNode<T,AT,TG>,
	                                         TaskGraphEdge<T,AT,TG>,
	                                         TaskGraphSearchOptions> {
	

}
