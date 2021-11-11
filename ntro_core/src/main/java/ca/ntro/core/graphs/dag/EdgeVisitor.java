package ca.ntro.core.graphs.dag;

public interface EdgeVisitor<N extends Node, E extends Edge> {
	
	void visitEdge(N from, E edge, N to);

}
