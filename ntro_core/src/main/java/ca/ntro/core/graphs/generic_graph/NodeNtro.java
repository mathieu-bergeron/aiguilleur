package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.ReachableStepVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.WalkReducer;
import ca.ntro.core.graphs.WalkVisitor;
import ca.ntro.core.wrappers.result.Result;

public abstract class NodeNtro<N extends Node<N,E,SO>, 
                               E extends Edge<N,E,SO>,
                               SO extends SearchOptions> 

      implements      Node<N,E,SO> {
	
	private NodeId nodeId;


	public NodeId getNodeId() {
		return nodeId;
	}

	public void setNodeId(NodeId nodeId) {
		this.nodeId = nodeId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			NodeNtro n = (NodeNtro) o;
			
			if(n.nodeId == null && nodeId != null) {
				return false;
			}

			if(n.nodeId != null && !n.nodeId.equals(nodeId)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public NodeNtro(NodeId nodeId) {
		setNodeId(nodeId);
	}

	@Override
	public NodeId id() {
		return getNodeId();
	}

	protected abstract <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer);
	protected abstract <R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, EdgeReducer<N,E,SO,R> reducer);

	@Override
	public void forEachEdge(EdgeVisitor<N,E,SO> visitor) {
		reduceEdges(null, (accumulator, e) -> {
			
			visitor.visitEdge(e);
			
			return null;

		}).throwException();;
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		return reduceEdgeNames(initialValue, (acc01, edgeName) -> {

			Result<R> res01 = reduceEdgesByName(edgeName, acc01, reducer);
			
			if(res01.hasException()) {
				throw res01.exception();
			}
			
			return res01.value();
		});
	}

	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(ReachableStepVisitor<N,E,SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(SO options, ReachableStepVisitor<N,E,SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue, ReachableStepReducer<N,E,SO,R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableStepReducer<N,E,SO,R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitWalk(WalkId walk, WalkVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

}
