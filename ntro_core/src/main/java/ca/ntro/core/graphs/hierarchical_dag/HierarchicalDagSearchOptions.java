package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraphSearchOptions;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;

public class HierarchicalDagSearchOptions extends HierarchicalGraphSearchOptions {

	public HierarchicalDagSearchOptions(Direction direction, int maxDistance) {
		super(new Direction[] {direction}, new OptionnalNtro<Integer>(maxDistance));
	}

}
