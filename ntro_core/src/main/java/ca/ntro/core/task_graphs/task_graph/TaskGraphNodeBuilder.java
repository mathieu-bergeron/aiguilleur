package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilder;

public interface TaskGraphNodeBuilder <T extends Task<T,AT>, 
                                      AT extends AtomicTask<T,AT>>

       extends TaskGraphNode<T,AT>,
               HierarchicalNodeBuilder<TaskGraphNode<T,AT>, 
                                       TaskGraphEdge<T,AT>, 
                                       TaskGraphSearchOptionsBuilder> {

}
