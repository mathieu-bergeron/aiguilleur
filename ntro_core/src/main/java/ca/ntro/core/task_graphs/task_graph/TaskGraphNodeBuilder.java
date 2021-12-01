package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilder;

public interface TaskGraphNodeBuilder <T extends Task<T,AT,TG>, 
                                      AT extends AtomicTask<T,AT,TG>,
                                      TG extends TaskGraph<T,AT,TG>> 

       extends TaskGraphNode<T,AT,TG>,
               HierarchicalNodeBuilder<TaskGraphNode<T,AT,TG>, 
                                       TaskGraphEdge<T,AT,TG>, 
                                       HierarchicalDagSearchOptions> {

}
