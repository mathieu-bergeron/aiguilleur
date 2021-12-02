package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Edge;

public interface TaskGraphEdge<T  extends Task<T,AT,TG>, 
                               AT extends AtomicTask<T,AT,TG>,
                               TG extends TaskGraph<T,AT,TG>> 

       extends Edge<TaskGraphNode<T,AT,TG>, 
                    TaskGraphEdge<T,AT,TG>, 
                    TaskGraphSearchOptionsBuilder> {
}
