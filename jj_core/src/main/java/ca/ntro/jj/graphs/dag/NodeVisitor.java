package ca.ntro.jj.graphs.dag;

public interface NodeVisitor<N extends Node> {
	
	void visitNode(N n);

}
