package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.ReachableStepVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.wrappers.result.Result;

public class      HierarchicalNodeNtro<N extends HierarchicalNode<N,E,SO>,
 									   E extends Edge<N,E,SO>,
 									   SO extends SearchOptions>

       extends    NodeNtro<N,E,SO> 

	   implements HierarchicalNode<N,E,SO> {


	public HierarchicalNodeNtro(NodeId id) {
		super(id);
	}

	@Override
	public boolean hasSubNodes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasParent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HierarchicalNode<N, E, SO> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachSubNode(ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachSubNode(SearchOptions options, ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceSubNodes(SearchOptions options, R initialValue,
			ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachParentNode(ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachParentNode(SearchOptions options, ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceParentNodes(SearchOptions options, R initialValue,
			ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected <R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachEdge(EdgeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(ReachableStepVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(SO options, ReachableStepVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue, ReachableStepReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableStepReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
}
