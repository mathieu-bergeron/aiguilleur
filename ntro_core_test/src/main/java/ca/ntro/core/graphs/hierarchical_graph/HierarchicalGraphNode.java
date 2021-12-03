package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.NodeId;

public abstract class HierarchicalGraphNode extends HierarchicalNodeBuilderNtro<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsBuilder> {

	public HierarchicalGraphNode(NodeId id,
			                     HierarchicalGraphBuilder<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsBuilder> graphBuilder) {
		super(id, graphBuilder);
	}

}
