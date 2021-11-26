package ca.ntro.core.graphs;

public interface EdgeReducer<NV extends NodeValue, 
                             EV extends EdgeValue, 
                             N extends Node<NV>,
                             E extends Edge<EV>,
                             R extends Object> {
	
	R reduceEdge(R accumulator, N from, E edge, N to) throws Throwable;

}
