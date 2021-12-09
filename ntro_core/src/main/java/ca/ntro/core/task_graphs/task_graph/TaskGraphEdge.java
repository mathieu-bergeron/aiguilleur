package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagEdge;

public interface TaskGraphEdge<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

       extends   HierarchicalDagEdge<TaskGraphNode<T,AT>, 
                                     TaskGraphEdge<T,AT>> {

}
