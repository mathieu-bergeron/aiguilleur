package ca.ntro.core.graphs;

public interface NodeReducer<NV extends NodeValue, R extends Object> {
	
	R reduceNode(R accumulator, Node<NV> n) throws Throwable;

}
