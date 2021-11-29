package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeNameNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class HierarchicalGraphEdge extends EdgeNtro<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptions> {

	public HierarchicalGraphEdge(HierarchicalGraphNode from, EdgeType edgeName, HierarchicalGraphNode to) {
		super(from, edgeName, to);
	}

	public HierarchicalGraphEdge(HierarchicalGraphNode from, String edgeName, HierarchicalGraphNode to) {
		super(from, new EdgeNameNtro(Direction.FORWARD, edgeName), to);
	}

}
