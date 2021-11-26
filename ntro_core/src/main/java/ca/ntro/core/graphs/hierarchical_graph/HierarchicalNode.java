package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface HierarchicalNode<NV extends NodeValue> extends Node<NV> {
	
	boolean isCluster();
	boolean isRootNode();

	HierarchicalNode<NV> parent();
	

}
