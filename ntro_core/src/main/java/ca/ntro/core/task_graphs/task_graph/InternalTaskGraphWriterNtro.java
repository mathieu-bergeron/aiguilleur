package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;

public class InternalTaskGraphWriterNtro<T  extends Task<T,AT>, 
                                         AT extends AtomicTask<T,AT>>

       extends InternalHierarchicalDagWriterNtro<TaskGraphNode<T,AT>,
	                                             TaskGraphEdge<T,AT>>

	   implements InternalTaskGraphWriter<T,AT> {

}
