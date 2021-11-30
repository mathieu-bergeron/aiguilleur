package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public interface TaskGraphEdge<T extends Task<AT>, AT extends AtomicTask> extends Edge<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>, HierarchicalDagSearchOptions> {

}
