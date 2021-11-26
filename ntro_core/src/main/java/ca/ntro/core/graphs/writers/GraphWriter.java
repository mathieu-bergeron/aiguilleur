package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface GraphWriter {
	
	void initialize(GraphId id, boolean directed);

	void writeEdge(Node<? extends NodeValue> from, Edge<? extends EdgeValue> edge, Node<? extends NodeValue> to);
	void writeNode(Node<? extends NodeValue> node);
	
	void writePng();
	void writeSvg();
	void writeDot();

}
