package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class GraphWriterNull implements GraphWriter {

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
	}

	@Override
	public void addEdge(Node<? extends NodeValue> from, 
			              Edge<? extends EdgeValue> edge, 
			              Node<? extends NodeValue> to) {
	}

	@Override
	public void addRootNode(Node<? extends NodeValue> node) {
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

	@Override
	public void addCluster(Node<? extends NodeValue> cluster) {
	}

	@Override
	public void addSubCluster(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subCluster) {
	}

	@Override
	public void addSubNode(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subNode) {
	}
}
