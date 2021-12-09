package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.graph.GraphSearchOptions;

public interface HierarchicalSearchOptions extends GraphSearchOptions {

	enum HierarchicalGraphDirection {
		SAME_LEVEL, UP_TO_PARENT_NODE, DOWN_TO_SUB_NODES
	}

}
