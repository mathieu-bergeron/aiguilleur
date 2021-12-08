package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.stream.Reducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure <N extends GenericNode<N,E,SO>, 
                                       E extends GenericEdge<N,E,SO>,
                                       SO extends SearchOptions> { 

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer);

	<R> void _reduceStartNodes(ResultNtro<R> result, Reducer<N,R> reducer);

}
