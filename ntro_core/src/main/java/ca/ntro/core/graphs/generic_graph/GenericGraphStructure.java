package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure <N extends Node<N,E,SO>, 
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptionsBuilder> { 

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer);

}
