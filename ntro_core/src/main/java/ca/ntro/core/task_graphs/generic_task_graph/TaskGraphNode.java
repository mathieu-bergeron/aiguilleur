package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNode;

public interface TaskGraphNode<T  extends GenericTask<T,AT>, 
                               AT extends GenericAtomicTask<T,AT>>

       extends   HierarchicalDagNode<TaskGraphNode<T,AT>, 
                                     TaskGraphEdge<T,AT>> {


	T task();

}
