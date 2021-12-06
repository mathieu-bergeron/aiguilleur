package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface HierarchicalGraphSearchOptionsBuilder extends GraphSearchOptionsBuilder {

	enum HierarchicalGraphDirection {
		SAME_LEVEL, UP_TO_PARENT_NODE, DOWN_TO_SUB_NODES
	}

}
