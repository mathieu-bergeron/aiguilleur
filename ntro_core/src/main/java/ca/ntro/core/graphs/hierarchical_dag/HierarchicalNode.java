package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.Node;
import ca.ntro.core.graphs.dag.NodeVisitor;

public interface HierarchicalNode extends Node {
	
	boolean isCluster();

	void forEachChildNode(NodeVisitor<HierarchicalNode> visitor);

	void forEachChildNodeTransitive(NodeVisitor<HierarchicalNode> visitor);

}
