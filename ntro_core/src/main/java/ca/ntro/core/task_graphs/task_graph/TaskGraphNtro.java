package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public class TaskGraphNtro<T extends Task<AT>, AT extends AtomicTask> {

	private HierarchicalDag<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>, HierarchicalDagSearchOptions> hdag;

}
