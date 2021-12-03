package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;

public abstract class HierarchicalGraphNode extends HierarchicalNodeBuilderNtro<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsBuilder> {

	public HierarchicalGraphNode(NodeId nodeId) {
		super(nodeId);
	}

	public HierarchicalGraphNode(String nodeId) {
		super(new NodeIdNtro(nodeId));
	}
}
