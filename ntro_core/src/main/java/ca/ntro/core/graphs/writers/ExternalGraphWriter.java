package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.Node;

public interface ExternalGraphWriter<N extends Node, E extends Edge> {
	
	void writeEdge(N from, E edge, N to);
	void writeNode(N node);

}
