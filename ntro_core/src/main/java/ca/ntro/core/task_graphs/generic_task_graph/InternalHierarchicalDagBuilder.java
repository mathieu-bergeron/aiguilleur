package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilder;

public interface InternalHierarchicalDagBuilder<T  extends GenericTask<T,AT>, 
                           						AT extends GenericAtomicTask<T,AT>>

       extends   HierarchicalDagBuilder<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>> {

}
