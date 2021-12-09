package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;

public interface GenericHierarchicalGraphBuilder<N extends GenericHierarchicalNode<N,E,SO>,
										         E extends GenericEdge<N,E,SO>,
										         SO extends HierarchicalSearchOptions,
										         NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
										         G extends GenericHierarchicalGraph<N,E,SO>>

       extends   GenericGraphBuilder<N,E,SO,NB,G> {


}
