package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;

public interface GenericHierarchicalGraphBuilder<N extends GenericHierarchicalNode<N,E,SO>,
										  E extends Edge<N,E,SO>,
										  SO extends HierarchicalGraphSearchOptionsBuilder,
										  NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
										  G extends GenericHierarchicalGraph<N,E,SO>>

       extends   GenericGraphBuilder<N,E,SO,NB,G> {


}
