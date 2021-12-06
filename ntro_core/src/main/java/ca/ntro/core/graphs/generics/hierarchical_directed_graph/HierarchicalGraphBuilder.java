package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;

public interface HierarchicalGraphBuilder<N extends HierarchicalNode<N,E,SO>,
										  E extends Edge<N,E,SO>,
										  SO extends HierarchicalGraphSearchOptionsBuilder,
										  NB extends HierarchicalNodeBuilder<N,E,SO,NB>,
										  G extends HierarchicalGraph<N,E,SO>>

       extends   GenericDirectedGraphBuilder<N,E,SO,NB,G> {


}
