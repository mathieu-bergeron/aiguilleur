package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.stream._Reducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure <N extends Node<N,E,SO>, 
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptionsBuilder> { 

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer);

	<R> void _reduceStartNodes(ResultNtro<R> result, _Reducer<N,R> reducer);

}
