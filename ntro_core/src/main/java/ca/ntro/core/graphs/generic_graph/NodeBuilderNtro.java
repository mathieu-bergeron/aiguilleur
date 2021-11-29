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
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirection;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirectionNtro;
import ca.ntro.core.wrappers.result.Result;

public class      NodeBuilderNtro<N extends Node<N,E,SO>, 
                                  E extends Edge<N,E,SO>,
                                  SO extends SearchOptions> 

      extends     NodeNtro<N,E,SO>

      implements  Node<N,E,SO>,
      	          NodeBuilder<N,E,SO> {

	public NodeBuilderNtro(NodeId nodeId) {
		super(nodeId);
	}

	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			NodeBuilderNtro<N,E,SO> n = (NodeBuilderNtro<N,E,SO>) o;
			
			if(n.edgesByDirection == null && edgesByDirection != null) {
				return false;
			}

			if(n.edgesByDirection != null && !n.edgesByDirection.equals(edgesByDirection)) {
				return false;
			}
			
			return super.equals(n);
		}

		return false;
	}

	@Override
	public void addEdge(E edge) {
		getEdgesByDirection().addEdge(edge);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
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
}