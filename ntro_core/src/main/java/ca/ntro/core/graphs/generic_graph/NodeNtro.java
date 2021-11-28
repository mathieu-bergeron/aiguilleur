package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
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
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirection;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirectionNtro;
import ca.ntro.core.wrappers.result.Result;

public abstract class NodeNtro<N extends Node<N,E,SO>, 
                               E extends Edge<N,E,SO>,
                               SO extends SearchOptions> 

      implements      Node<N,E,SO>,
      	              NodeBuilder<N,E,SO> {
	
	private NodeId nodeId;

	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public NodeId getNodeId() {
		return nodeId;
	}

	public void setNodeId(NodeId nodeId) {
		this.nodeId = nodeId;
	}

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
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

			if(n.edgesByDirection == null && edgesByDirection != null) {
				return false;
			}

			if(n.edgesByDirection != null && !n.edgesByDirection.equals(edgesByDirection)) {
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

	@Override
	public void addEdge(E edge) {
		getEdgesByDirection().addEdge(edge);
	}

	@Override
	public N toNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(ReachableStepVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(SO options, ReachableStepVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue, ReachableStepReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableStepReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitWalk(N fromNode, WalkId walk, WalkVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceWalk(N fromNode, WalkId walk, R initialValue, WalkReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void forEachEdge(EdgeVisitor<N, E> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitWalk(WalkId walk, WalkVisitor<N, E, SO> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
}
