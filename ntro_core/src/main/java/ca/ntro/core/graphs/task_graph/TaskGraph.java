package ca.ntro.core.graphs.task_graph;

import ca.ntro.core.graphs.EdgeValueNull;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;

public interface TaskGraph<T extends Task, AT extends AtomicTask> extends HierarchicalDag<T, EdgeValueNull> {

}
