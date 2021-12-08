package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.exceptions.Break;

public interface ReachableEdgeVisitor<N extends GenericNode<N,E,SO>,
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> {
	
	void visitReachableEdge(Walk<N,E,SO> walked, E edge) throws Break;

}
