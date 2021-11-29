package ca.ntro.core.graphs.tests;


import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.NodeBuilderNtro;

public class GraphNode extends NodeBuilderNtro<GraphNode, GraphEdge, SearchOptions> {

	public GraphNode(NodeId nodeId) {
		super(nodeId);
	}

	public GraphNode(String nodeId) {
		super(new NodeIdNtro(nodeId));
	}
}
