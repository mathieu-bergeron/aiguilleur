package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.structure.EdgesByDirection;
import ca.ntro.core.graphs.generics.graph.structure.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;

public abstract class GenericNodeBuilderNtro<N extends GenericNode<N,E,SO>, 
                                             E extends GenericEdge<N,E,SO>,
                                             SO extends SearchOptions,
                                             NB extends GenericNodeBuilder<N,E,SO,NB>> 

      implements      GenericNodeBuilder<N,E,SO,NB> {

	private N node;
	private GenericGraphBuilderNtro<N,E,SO,NB,?,?> graphBuilder;

	private boolean isStartNode = true;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public GenericGraphBuilderNtro<N,E,SO,NB,?,?> getGraphBuilder() {
		return graphBuilder;
	}

	public void setGraphBuilder(GenericGraphBuilderNtro<N,E,SO,NB,?,?> genericGraphBuilderNtro) {
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

	public GenericGraph<N,E,SO,?> parentGraph(){
		return getGraphBuilder().graph();
	}

	protected GenericGraphBuilder<N,E,SO,NB,?,?> graphBuilder(){
		return getGraphBuilder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E addEdge(String edgeName, NB toNode) {
		return graphBuilder().addEdge((NB) this, edgeName, toNode);
	}

	public void addEdge(E edge) {

		if(ifEdgeAlreadyExists(edge)) {

			Ntro.exceptions().throwException(new EdgeAlreadyAddedException("Edge already added: " +  edge.id().toKey()));
			
		}else {

			getEdgesByDirection().addEdge(edge);
		}
	}
	
	public boolean ifEdgeAlreadyExists(E edge) {
		boolean alreadyAdded = false;
		
		if(edge.from() != edge.to()) {

			alreadyAdded = node().edges().ifSome(e -> {
				return e.equalsUndirected(edge);
			});

		}else {

			alreadyAdded = node().edges().ifSome(e -> {
				return e.equals(edge);
			});
		}
		
		return alreadyAdded;
	}

	@Override
	public N node() {
		return getNode();
	}

	@Override
	public Stream<EdgeType> edgeTypes(Direction direction){
		return getEdgesByDirection().edgeTypes(direction);
	}
	
	@Override
	public Stream<E> edges(EdgeType edgeType){
		return getEdgesByDirection().edges(edgeType);
	}

	protected SO defaultSearchOptions() {
		return getGraphBuilder().graph().defaultSearchOptions();
	}

	public boolean isStartNode() {
		return getIsStartNode();
	}
}
