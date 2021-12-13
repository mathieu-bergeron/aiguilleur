package ca.ntro.core.graph_writer;

import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;

public interface GraphWriter {
	
	void initialize(GraphId id, GraphWriterOptions options);

	void addNode(NodeSpec nodeSpec) throws GraphWriterException;
	void addCluster(NodeSpec clusterSpec) throws GraphWriterException;

	void addSubCluster(NodeSpec clusterSpec, NodeSpec subClusterSpec) throws GraphWriterException;
	void addSubNode(NodeSpec clusterSpec, NodeSpec subNodeSpec) throws GraphWriterException;

	void addEdge(EdgeSpec edgeSpec) throws GraphWriterException;
	
	void writePng();
	void writeSvg();
	void writeDot();

}
