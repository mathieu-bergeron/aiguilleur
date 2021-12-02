package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;

public class InternalTaskGraphWriterNtro<T  extends Task<T,AT,TG>, 
                                         AT extends AtomicTask<T,AT,TG>,
                                         TG extends TaskGraph<T,AT>> 

       extends InternalHierarchicalDagWriterNtro<TaskGraphNode<T,AT,TG>,
	                                             TaskGraphEdge<T,AT,TG>,
	                                             TaskGraphSearchOptionsBuilder>

	   implements InternalTaskGraphWriter<T,AT,TG> {

}
