package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface TaskGraphNode<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

       extends HierarchicalNode<TaskGraphNode<T,AT>, 
                                TaskGraphEdge<T,AT>, 
                                TaskGraphSearchOptionsBuilder> {

	T task();

}
