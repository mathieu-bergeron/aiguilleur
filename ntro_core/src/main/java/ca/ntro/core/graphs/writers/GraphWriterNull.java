package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class GraphWriterNull implements GraphWriter {

	@Override
	public void initialize(GraphId id, boolean directed) {
	}

	@Override
	public void writeEdge(Node<? extends NodeValue> from, 
			              Edge<? extends EdgeValue> edge, 
			              Node<? extends NodeValue> to) {
	}

	@Override
	public void writeNode(Node<? extends NodeValue> node) {
	}

	@Override
	public void writePng() {
	}

	@Override
	public void writeSvg() {
	}

	@Override
	public void writeDot() {
	}

}
