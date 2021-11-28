package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.wrappers.result.Result;

public class      HierarchicalNodeNtro<NV extends NodeValue,
                                       EV extends EdgeValue,
                                       N extends HierarchicalNode<NV,EV,N,E>,
                                       E extends Edge<EV>> 

       extends    NodeNtro<NV> 

	   implements HierarchicalNode<NV,EV,N,E> {


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
	public HierarchicalNode<NV, EV, N, E> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachSubNode(ReachableNodeVisitor<NV, EV, N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachSubNode(SearchOptions options, ReachableNodeVisitor<NV, EV, N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<NV, EV, N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceSubNodes(SearchOptions options, R initialValue,
			ReachableNodeReducer<NV, EV, N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachParentNode(ReachableNodeVisitor<NV, EV, N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachParentNode(SearchOptions options, ReachableNodeVisitor<NV, EV, N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<NV, EV, N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceParentNodes(SearchOptions options, R initialValue,
			ReachableNodeReducer<NV, EV, N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}


}
