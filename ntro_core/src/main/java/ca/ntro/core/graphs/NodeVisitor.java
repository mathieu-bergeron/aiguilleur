package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsNtro> {
	
	void visitNode(N n) throws Break;

}
