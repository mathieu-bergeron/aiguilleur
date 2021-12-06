package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {
	
	void visitEdge(E edge) throws Break;

}
