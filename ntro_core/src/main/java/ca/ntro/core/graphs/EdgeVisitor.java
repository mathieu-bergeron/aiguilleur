package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptions> {
	
	void visitEdge(E edge) throws Break;

}
