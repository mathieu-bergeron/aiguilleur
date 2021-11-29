package ca.ntro.core.graphs.hierarchical_graph;


import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.SearchOptions;

public class HierarchicalGraphNode extends HierarchicalNodeBuilderNtro<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptions> {

	public HierarchicalGraphNode(NodeId nodeId) {
		super(nodeId);
	}

	public HierarchicalGraphNode(String nodeId) {
		super(new NodeIdNtro(nodeId));
	}
}
