package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriter;

public interface InternalTaskGraphWriter<T  extends Task<T,AT>, 
                                         AT extends AtomicTask<T,AT>>

       extends InternalHierarchicalDagWriter<TaskGraphNode<T,AT>,
	                                         TaskGraphEdge<T,AT>> {
	

}
