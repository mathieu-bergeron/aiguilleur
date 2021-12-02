package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Edge;

public interface TaskGraphEdge<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

       extends Edge<TaskGraphNode<T,AT>, 
                    TaskGraphEdge<T,AT>, 
                    TaskGraphSearchOptionsBuilder> {
}
