package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface HierarchicalGraphBuilder<N extends HierarchicalNode<N,E,SO>,
										  E extends Edge<N,E,SO>,
										  SO extends HierarchicalGraphSearchOptionsBuilder,
										  NB extends HierarchicalNodeBuilder<N,E,SO,NB>>

       extends   GenericGraphBuilder<N,E,SO,NB,HierarchicalGraph<N,E,SO>> {


}
