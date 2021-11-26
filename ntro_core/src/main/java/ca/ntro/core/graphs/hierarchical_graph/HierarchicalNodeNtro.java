package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.NodeNtro;

public class      HierarchicalNodeNtro<NV extends NodeValue> 

       extends    NodeNtro<NV> 

	   implements HierarchicalNode<NV> {


	public HierarchicalNodeNtro(NodeId id, NV value) {
		super(id, value);
	}

	@Override
	public boolean isCluster() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRootNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HierarchicalNode<NV> parent() {
		// TODO Auto-generated method stub
		return null;
	}

}
