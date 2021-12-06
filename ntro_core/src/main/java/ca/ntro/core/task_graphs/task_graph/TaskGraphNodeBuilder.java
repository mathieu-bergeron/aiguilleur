package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeBuilder;

public interface TaskGraphNodeBuilder <T extends Task<T,AT>, 
                                      AT extends AtomicTask<T,AT>>

       extends TaskGraphNode<T,AT>,
               GenericHierarchicalNodeBuilder<TaskGraphNode<T,AT>, 
                                       TaskGraphEdge<T,AT>, 
                                       TaskGraphSearchOptionsBuilder,
                                       TaskGraphNodeBuilder<T,AT>> {

}
