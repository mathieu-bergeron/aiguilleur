package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.graph.Edge;
import ca.ntro.core.graphs.graph.Node;

public interface ExternalGraphWriter<N extends Node, E extends Edge> {
	
	void writeEdge(N from, E edge, N to);
	void writeNode(N node);
	
	void writePng();
	void writeSvg();
	void writeDot();

}
