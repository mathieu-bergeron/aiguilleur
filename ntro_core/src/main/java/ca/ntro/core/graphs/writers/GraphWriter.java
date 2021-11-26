package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface GraphWriter {
	
	void initialize(GraphId id, GraphWriterOptions options);

	void addCluster(Node<? extends NodeValue> cluster);
	void addSubCluster(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subCluster);
	void addSubNode(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subNode);

	void addRootNode(Node<? extends NodeValue> node);

	void addEdge(Node<? extends NodeValue> from, Edge<? extends EdgeValue> edge, Node<? extends NodeValue> to);
	
	void writePng();
	void writeSvg();
	void writeDot();

}
