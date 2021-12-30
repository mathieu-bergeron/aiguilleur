package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagEdge;

public interface TaskGraphEdge<T  extends GenericTask<T,AT>, 
                               AT extends GenericAtomicTask<T,AT>>

       extends   HierarchicalDagEdge<TaskGraphNode<T,AT>, 
                                     TaskGraphEdge<T,AT>> {

}
