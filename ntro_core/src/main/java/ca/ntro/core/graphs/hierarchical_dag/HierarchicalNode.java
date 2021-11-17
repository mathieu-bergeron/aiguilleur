package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeVisitor;

public interface HierarchicalNode extends Node {
	
	boolean isCluster();

	void forEachChildNode(NodeVisitor<HierarchicalNode> visitor);

	void forEachChildNodeTransitive(NodeVisitor<HierarchicalNode> visitor);

}
