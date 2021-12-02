package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.NodeBuilderNtro;

public class GraphNode extends NodeBuilderNtro<GraphNode, GraphEdge, GraphSearchOptions> {

	public GraphNode(NodeId nodeId) {
		super(nodeId);
	}

	public GraphNode(String nodeId) {
		super(new NodeIdNtro(nodeId));
	}

	@Override
	protected GraphEdge createEdge(GraphNode fromNode, EdgeType edgeType, GraphNode toNode) {
		return new GraphEdge(fromNode, edgeType, toNode);
	}
}
