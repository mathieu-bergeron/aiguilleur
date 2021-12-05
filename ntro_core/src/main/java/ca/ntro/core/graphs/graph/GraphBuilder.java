package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public interface GraphBuilder<N extends Node<N,E,SO>,
							  E extends Edge<N,E,SO>,
							  SO extends GraphSearchOptionsBuilder,
							  NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends GenericGraphBuilder<N,E,SO,NB,Graph<N,E,SO>> {

}
