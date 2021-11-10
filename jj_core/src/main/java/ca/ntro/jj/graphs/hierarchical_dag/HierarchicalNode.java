package ca.ntro.jj.graphs.hierarchical_dag;

import ca.ntro.jj.graphs.dag.Node;
import ca.ntro.jj.graphs.dag.NodeVisitor;

public interface HierarchicalNode extends Node {
	
	boolean isCluster();

	void forEachChildNode(NodeVisitor<HierarchicalNode> visitor);

	void forEachChildNodeTransitive(NodeVisitor<HierarchicalNode> visitor);

}
