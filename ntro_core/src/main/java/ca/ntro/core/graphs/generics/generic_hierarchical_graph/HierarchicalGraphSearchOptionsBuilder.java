package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.graph.GraphSearchOptionsBuilder;

public interface HierarchicalGraphSearchOptionsBuilder extends GraphSearchOptionsBuilder {

	enum HierarchicalGraphDirection {
		SAME_LEVEL, UP_TO_PARENT_NODE, DOWN_TO_SUB_NODES
	}

}
