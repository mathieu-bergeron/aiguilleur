package ca.ntro.core.graphs.dag;

public interface DagWriter<N extends Node, E extends Edge> {
	
	void writeNode(N node);
	void writeEdge(E edge);

}
