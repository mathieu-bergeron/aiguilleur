package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> {
	
	void visitEdge(E edge) throws Break;

}
