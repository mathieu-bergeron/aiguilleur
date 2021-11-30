package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.SearchOptions;

public interface TaskGraphEdge<AT extends AtomicTask> extends Edge<Task<AT>, TaskGraphEdge<AT>, SearchOptions>{

}
