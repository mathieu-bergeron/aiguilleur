package ca.ntro.core.graphs;

public interface EdgeReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceEdge(R accumulator, Node<NV> from, Edge<EV> edge, Node<NV> to) throws Throwable;

}
