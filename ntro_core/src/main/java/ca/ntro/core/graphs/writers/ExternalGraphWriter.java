package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.Node;
import ca.ntro.core.graphs.generic_graph.NodeValue;

public interface ExternalGraphWriter<NV extends NodeValue, EV extends EdgeValue> {
	
	void writeEdge(Node<NV> from, Edge<EV> edge, Node<NV> to);
	void writeNode(Node<NV> node);
	
	void writePng();
	void writeSvg();
	void writeDot();

}
