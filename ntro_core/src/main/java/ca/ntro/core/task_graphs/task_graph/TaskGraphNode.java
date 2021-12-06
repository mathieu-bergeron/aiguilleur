package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalNode;

public interface TaskGraphNode<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

       extends GenericHierarchicalNode<TaskGraphNode<T,AT>, 
                                TaskGraphEdge<T,AT>, 
                                TaskGraphSearchOptionsBuilder> {

	T task();

}
