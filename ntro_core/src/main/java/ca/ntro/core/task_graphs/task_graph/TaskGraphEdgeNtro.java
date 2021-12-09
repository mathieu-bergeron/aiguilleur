package ca.ntro.core.task_graphs.task_graph;

public class    TaskGraphEdgeNtro<T  extends Task<T,AT>, 
                                  AT extends AtomicTask<T,AT>>

       extends  HierarchicalDagEdgeNtro<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>>

       implements TaskGraphEdge<T,AT> {

}
