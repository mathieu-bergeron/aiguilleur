package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface TaskGraphNode<T extends Task<AT>, AT extends AtomicTask> extends HierarchicalNode<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>, HierarchicalDagSearchOptions>{
	
	T task();

}
