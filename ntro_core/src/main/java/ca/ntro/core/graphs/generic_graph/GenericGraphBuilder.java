package ca.ntro.core.graphs.generic_graph;

public interface GenericGraphBuilder<SO extends SearchOptions, N extends Node, E extends Edge, G extends GenericGraph<SO,N,E>> {

	void addNode(N n);

	void addEdge(N from, E edge, N to);
	
	G toGraph();

}
