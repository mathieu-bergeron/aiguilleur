package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeValueNull;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;

public class TaskGraphNtro<T extends Task<AT>, AT extends AtomicTask> {
	
	private HierarchicalDag<T, EdgeValueNull, NodeNtro<T>, EdgeNtro<EdgeValueNull>> dag;


}
