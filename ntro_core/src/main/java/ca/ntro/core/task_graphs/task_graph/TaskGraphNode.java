package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNode;

public interface TaskGraphNode<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

       extends   HierarchicalDagNode<TaskGraphNode<T,AT>, 
                                     TaskGraphEdge<T,AT>> {


	T task();

}
