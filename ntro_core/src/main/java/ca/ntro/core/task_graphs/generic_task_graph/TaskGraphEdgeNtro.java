package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagEdgeNtro;

public class    TaskGraphEdgeNtro<T  extends GenericTask<T,AT>, 
                                  AT extends GenericAtomicTask<T,AT>>

       extends  HierarchicalDagEdgeNtro<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>>

       implements TaskGraphEdge<T,AT> {

}
