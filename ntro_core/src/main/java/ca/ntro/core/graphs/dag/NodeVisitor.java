package ca.ntro.core.graphs.dag;

public interface NodeVisitor<N extends Node> {
	
	void visitNode(N n);

}
