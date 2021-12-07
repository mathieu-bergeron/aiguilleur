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

	private Class<N> nodeClass;
	private Class<E> edgeClass;
	
	private N node;

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

	public Class<N> getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(Class<N> nodeClass) {
		this.nodeClass = nodeClass;
	}

	public Class<E> getEdgeClass() {
		return edgeClass;
	}

	public void setEdgeClass(Class<E> edgeClass) {
		this.edgeClass = edgeClass;
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

	@SuppressWarnings("unchecked")
	protected GenericGraphBuilder<N,E,SO,NB,?> graphBuilder(){
		return ((GenericGraphBuilderNtro<N,E,SO,NB,?>) parentGraph());
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
	
	@SuppressWarnings("unchecked")
	@Override
	public N node() {
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

	protected SO defaultSearchOptions() {
		return getGraphBuilder().graph().defaultSearchOptions();
	}

	public boolean isStartNode() {
		return getIsStartNode();
	}

	@Override
	public void setNodeName(String nodeName) {
		// TODO Auto-generated method stub
		
	}

}
