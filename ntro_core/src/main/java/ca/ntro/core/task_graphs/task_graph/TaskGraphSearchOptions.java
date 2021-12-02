package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public class TaskGraphSearchOptions extends HierarchicalDagSearchOptions<TaskGraphSearchOptions>{

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.DOWN, Direction.FORWARD};
	}

}
