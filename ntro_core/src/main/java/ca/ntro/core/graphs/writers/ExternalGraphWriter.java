package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.Node;

public interface ExternalGraphWriter<N extends Node, E extends Edge> {
	
	void writeEdge(N from, E edge, N to);
	void writeNode(N node);
	
	void writePng();
	void writeSvg();
	void writeDot();

}
