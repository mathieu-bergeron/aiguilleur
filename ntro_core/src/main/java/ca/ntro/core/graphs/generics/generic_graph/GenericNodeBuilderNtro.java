package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.graphs.generics.generic_graph_structure.EdgesByDirection;
import ca.ntro.core.graphs.generics.generic_graph_structure.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericNodeBuilderNtro<N extends Node<N,E,SO>, 
                                             E extends Edge<N,E,SO>,
                                             SO extends SearchOptionsBuilder,
                                             NB extends GenericNodeBuilder<N,E,SO,NB>> 

      implements      GenericNodeBuilder<N,E,SO,NB> {

	private N node;
	private GenericGraphBuilderNtro<N,E,SO, NB, ?> graphBuilder;

	private boolean isStartNode = true;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public GenericGraphBuilderNtro<N,E,SO,NB,?> getGraphBuilder() {
		return graphBuilder;
	}

	public void setGraphBuilder(GenericGraphBuilderNtro<N, E, SO, NB, ?> genericGraphBuilderNtro) {
		this.graphBuilder = genericGraphBuilderNtro;
	}

	public boolean getIsStartNode() {
		return isStartNode;
	}

	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public N getNode() {
		return node;
	}

	public void setNode(N node) {
		this.node = node;
	}

	public void setStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
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

	public GenericGraph<N,E,SO> parentGraph(){
		return getGraphBuilder().graph();
	}

	protected GenericGraphBuilder<N,E,SO,NB,?> graphBuilder(){
		return getGraphBuilder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E addEdge(String edgeName, NB toNode) {
		return graphBuilder().addEdge((NB) this, edgeName, toNode);
	}

	@Override
	public void addEdge(E edge) {

		if(getEdgesByDirection().containsEdge(edge)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("Edge already added: " +  edge.id().toKey()));
			
		}else {

			getEdgesByDirection().addEdge(edge);
		}
	}

	@Override
	public N node() {
		return getNode();
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

	protected SO defaultSearchOptions() {
		return getGraphBuilder().graph().defaultSearchOptions();
	}

	public boolean isStartNode() {
		return getIsStartNode();
	}
}