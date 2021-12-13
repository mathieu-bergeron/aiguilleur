package ca.ntro.core.graph_writer;

import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;

public class GraphWriterNull implements GraphWriter {

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNode(NodeSpec nodeSpec) throws GraphWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCluster(NodeSpec clusterSpec) throws GraphWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubCluster(NodeSpec clusterSpec, NodeSpec subClusterSpec) throws GraphWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubNode(NodeSpec clusterSpec, NodeSpec subNodeSpec) throws GraphWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(EdgeSpec edgeSpec) throws GraphWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writePng() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSvg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeDot() {
		// TODO Auto-generated method stub
		
	}

}
