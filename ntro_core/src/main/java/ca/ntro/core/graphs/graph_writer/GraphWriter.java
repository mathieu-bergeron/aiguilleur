package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.graphs.generics.generic_graph.GraphId;

public interface GraphWriter {
	
	void initialize(GraphId id, GraphWriterOptions options);

	void addNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException;
	void addCluster(ClusterSpec clusterSpec) throws ClusterAlreadyAddedException;

	void addSubCluster(ClusterSpec clusterSpec, ClusterSpec subClusterSpec) throws ClusterNotFoundException, ClusterAlreadyAddedException;
	void addSubNode(ClusterSpec clusterSpec, NodeSpec subNodeSpec) throws ClusterNotFoundException, NodeAlreadyAddedException;

	void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, EdgeAlreadyAddedException;
	void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException, EdgeAlreadyAddedException;
	void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException, EdgeAlreadyAddedException;
	void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws ClusterNotFoundException, EdgeAlreadyAddedException;
	
	void writePng();
	void writeSvg();
	void writeDot();

}
