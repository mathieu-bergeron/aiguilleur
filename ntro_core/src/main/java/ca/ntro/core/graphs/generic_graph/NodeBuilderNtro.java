package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirection;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class NodeBuilderNtro<N extends Node<N,E,SO>, 
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> 

      extends         NodeNtro<N,E,SO>

      implements      Node<N,E,SO>,
      	              NodeBuilder<N,E,SO> {

	private GenericGraphBuilderNtro<N,E,SO, GenericGraph<N,E,SO>> graph;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public GenericGraphBuilderNtro<N, E, SO, GenericGraph<N, E, SO>> getGraph() {
		return graph;
	}

	public void setGraph(GenericGraphBuilderNtro<N, E, SO, GenericGraph<N, E, SO>> graph) {
		this.graph = graph;
	}

	public NodeBuilderNtro(NodeId nodeId, GenericGraphBuilderNtro<N,E,SO,GenericGraph<N,E,SO>> graph) {
		super(nodeId);
		setGraph(graph);
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
	public GenericGraph<N,E,SO> parentGraph(){
		return getGraph();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addEdge(String edgeName, N to) {
		EdgeTypeNtro edgeForward = new EdgeTypeNtro(Direction.FORWARD, edgeName);
		EdgeTypeNtro edgeBackward = new EdgeTypeNtro(Direction.BACKWARD, edgeName);
		
		addEdge(edgeForward, to);

		((NodeBuilderNtro<N,E,SO>) to).addEdge(edgeBackward, this.toNode());
	}

	protected void addEdge(EdgeType edgeType, N to) {

		E edge = createEdge(this.toNode(), edgeType, to);
		
		if(getEdgesByDirection().containsEdge(edge)) {
			
			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("Edge already added: " +  edgeType));
			
		}else {

			getEdgesByDirection().addEdge(edge);
		}
	}
	
	protected abstract E createEdge(N fromNode, EdgeType edgeType, N toNode);

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@Override
	protected <R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		getEdgesByDirection()._reduceEdgeTypesForDirection(direction, result, reducer);
	}

	@Override
	protected <R> void _reduceEdgesByType(EdgeType edgeType, 
			                              ResultNtro<R> result,
			                              EdgeReducer<N,E,SO,R> reducer) {

		getEdgesByDirection()._reduceEdgesByType(edgeType, result, reducer);
	}

	@Override
	protected SO defaultSearchOptions() {
		return (SO) new SearchOptionsNtro();
	}
}
