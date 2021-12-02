package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;

public class InternalTaskGraphWriterNtro<T  extends Task<T,AT,TG>, 
                                         AT extends AtomicTask<T,AT,TG>,
                                         TG extends TaskGraph<T,AT,TG>> 

       extends InternalHierarchicalDagWriterNtro<TaskGraphNode<T,AT,TG>,
	                                             TaskGraphEdge<T,AT,TG>,
	                                             TaskGraphSearchOptions>

	   implements InternalTaskGraphWriter<T,AT,TG> {

}
