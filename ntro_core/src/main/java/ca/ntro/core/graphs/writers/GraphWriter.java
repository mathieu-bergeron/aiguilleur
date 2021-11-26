package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeNotFoundException;

public interface GraphWriter {
	
	void initialize(GraphId id, GraphWriterOptions options);

	void addNode(NodeSpec nodeSpec);
	void addCluster(ClusterSpec clusterSpec);

	void addSubCluster(ClusterSpec clusterSpec, ClusterSpec subClusterSpec) throws ClusterNotFoundException;
	void addSubNode(ClusterSpec clusterSpec, NodeSpec subNodeSpec) throws ClusterNotFoundException;

	void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException;
	void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException;
	void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException;
	void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws ClusterNotFoundException;
	
	void writePng();
	void writeSvg();
	void writeDot();

}
