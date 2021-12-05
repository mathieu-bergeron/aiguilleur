package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public interface HierarchicalNodeBuilder<N  extends HierarchicalNode<N,E,SO>,
                                         E  extends Edge<N,E,SO>,
                                         SO extends HierarchicalGraphSearchOptionsBuilder,
                                         NB extends HierarchicalNodeBuilder<N,E,SO,NB>>

       extends   GenericNodeBuilder<N,E,SO,NB> {

	void addSubNode(NB subNode);

}
