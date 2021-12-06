package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;

public interface GenericHierarchicalGraphBuilder<N extends GenericHierarchicalNode<N,E,SO>,
										  E extends Edge<N,E,SO>,
										  SO extends HierarchicalGraphSearchOptionsBuilder,
										  NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
										  G extends GenericHierarchicalGraph<N,E,SO>>

       extends   GenericDirectedGraphBuilder<N,E,SO,NB,G> {


}
