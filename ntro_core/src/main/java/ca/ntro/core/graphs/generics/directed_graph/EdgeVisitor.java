package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {
	
	void visitEdge(E edge) throws Break;

}
