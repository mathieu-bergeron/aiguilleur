package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeValueNull;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeNtro;

public class TaskGraphNtro<T extends Task<AT>, AT extends AtomicTask> {

	private HierarchicalDag<T, EdgeValueNull, HierarchicalNodeNtro<T>, EdgeNtro<EdgeValueNull>> hdag;

}
