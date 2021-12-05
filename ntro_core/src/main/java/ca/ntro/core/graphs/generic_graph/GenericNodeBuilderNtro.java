package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirection;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericNodeBuilderNtro<N extends Node<N,E,SO>, 
                                             E extends Edge<N,E,SO>,
                                             SO extends SearchOptionsBuilder,
                                             NB extends GenericNodeBuilder<N,E,SO,NB>> 

      extends         GenericNodeNtro<N,E,SO>

      implements      Node<N,E,SO>,
      	              GenericNodeBuilder<N,E,SO,NB> {

	private boolean isStartNode = true;
	private GenericGraphBuilder<N,E,SO, NB, GenericGraph<N,E,SO>> graphBuilder;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public GenericGraphBuilder<N,E,SO,NB,GenericGraph<N, E, SO>> getGraphBuilder() {
		return graphBuilder;
	}

	public void setGraphBuilder(GenericGraphBuilder<N,E,SO,NB,GenericGraph<N, E, SO>> graph) {
		this.graphBuilder = graph;
	}

	public boolean getIsStartNode() {
		return isStartNode;
	}

	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public GenericNodeBuilderNtro(NodeId nodeId, GenericGraphBuilder<N,E,SO,NB,GenericGraph<N,E,SO>> graphBuilder) {
		super(nodeId);
		setGraphBuilder(graphBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof GenericNodeNtro) {
			GenericNodeBuilderNtro<N,E,SO,NB> n = (GenericNodeBuilderNtro<N,E,SO,NB>) o;
			
			if(n.edgesByDirection == null && edgesByDirection != null) {
				return false;
			}

			if(n.edgesByDirection != null && !n.edgesByDirection.equals(edgesByDirection)) {
				return false;
			}

			if(n.isStartNode != isStartNode) {
				return false;
			}
			
			return super.equals(n);
		}

		return false;
	}

	@Override
	public GenericGraph<N,E,SO> parentGraph(){
		return getGraphBuilder().toGraph();
	}

	@SuppressWarnings("unchecked")
	protected GenericGraphBuilder<N,E,SO,NB,?> graphBuilder(){
		return ((GenericGraphBuilderNtro<N,E,SO,NB,?>) parentGraph());
	}

	@Override
	public E addEdge(String edgeName, N toNode) {
		return graphBuilder().addEdge(this.toNode(), edgeName, toNode);
	}

	protected void addEdge(E edge) {

		if(getEdgesByDirection().containsEdge(edge)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("Edge already added: " +  edge.id().toKey()));
			
		}else {

			getEdgesByDirection().addEdge(edge);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@Override
	public <R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		getEdgesByDirection()._reduceEdgeTypesForDirection(direction, result, reducer);
	}

	@Override
	public <R> void reduceEdgesByType(EdgeType edgeType, 
			                          ResultNtro<R> result,
			                          EdgeReducer<N,E,SO,R> reducer) {

		getEdgesByDirection()._reduceEdgesByType(edgeType, result, reducer);
	}

	@Override
	protected SO defaultSearchOptions() {
		return getGraphBuilder().toGraph().defaultSearchOptions();
	}

	@Override
	public boolean isStartNode() {
		return getIsStartNode();
	}
}
