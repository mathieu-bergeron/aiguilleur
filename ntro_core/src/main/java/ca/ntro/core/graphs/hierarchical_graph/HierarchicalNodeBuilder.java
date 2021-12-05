package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public interface HierarchicalNodeBuilder<N  extends HierarchicalNode<N,E,SO>,
                                         E  extends Edge<N,E,SO>,
                                         SO extends HierarchicalGraphSearchOptionsBuilder>

       extends   GenericNodeBuilder<N,E,SO> {

	void addSubNode(N subNode);

}
