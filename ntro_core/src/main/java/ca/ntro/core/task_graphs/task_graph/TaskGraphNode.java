package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface TaskGraphNode<T  extends Task<T,AT,TG>, 
                               AT extends AtomicTask<T,AT,TG>,
                               TG extends TaskGraph<T,AT>> 

       extends HierarchicalNode<TaskGraphNode<T,AT,TG>, 
                                TaskGraphEdge<T,AT,TG>, 
                                TaskGraphSearchOptionsBuilder> {

	T task();

}
