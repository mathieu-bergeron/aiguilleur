package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.GraphId;

public class GraphWriterNull implements GraphWriter {

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
	}

	@Override
	public void addNode(NodeSpec nodeSpec) {
	}

	@Override
	public void addCluster(ClusterSpec clusterSpec) {
	}

	@Override
	public void addSubCluster(ClusterSpec clusterSpec, ClusterSpec subClusterSpec) {
	}

	@Override
	public void addSubNode(ClusterSpec clusterSpec, NodeSpec subNodeSpec) {
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) {
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) {
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) {
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) {
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
