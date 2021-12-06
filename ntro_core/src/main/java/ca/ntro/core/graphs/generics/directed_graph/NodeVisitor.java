package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<N extends GenericNode<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {
	
	void visitNode(N n) throws Break;

}
