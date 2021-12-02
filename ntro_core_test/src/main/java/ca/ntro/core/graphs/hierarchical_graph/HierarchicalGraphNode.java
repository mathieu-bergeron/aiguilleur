package ca.ntro.core.graphs.hierarchical_graph;


import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;

public class HierarchicalGraphNode extends HierarchicalNodeBuilderNtro<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsMock> {

	public HierarchicalGraphNode(NodeId nodeId) {
		super(nodeId);
	}

	public HierarchicalGraphNode(String nodeId) {
		super(new NodeIdNtro(nodeId));
	}
}
