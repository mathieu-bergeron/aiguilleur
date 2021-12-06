package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.graph_structure.EdgesByDirection;
import ca.ntro.core.graphs.generics.directed_graph.graph_structure.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericNodeBuilderNtro<N extends GenericNode<N,E,SO>, 
                                             E extends Edge<N,E,SO>,
                                             SO extends SearchOptionsBuilder,
                                             NB extends GenericNodeBuilder<N,E,SO,NB>> 

      extends         GenericNodeNtro<N,E,SO>

      implements      GenericNode<N,E,SO>,
      	              GenericNodeBuilder<N,E,SO,NB> {

	private boolean isStartNode = true;
	private GenericDirectedGraphBuilder<N,E,SO, NB, GenericDirectedGraph<N,E,SO>> graphBuilder;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public GenericDirectedGraphBuilder<N,E,SO,NB,GenericDirectedGraph<N, E, SO>> getGraphBuilder() {
		return graphBuilder;
	}

	public void setGraphBuilder(GenericDirectedGraphBuilder<N,E,SO,NB,GenericDirectedGraph<N, E, SO>> graph) {
		this.graphBuilder = graph;
	}

	public boolean getIsStartNode() {
		return isStartNode;
	}

	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public GenericNodeBuilderNtro(NodeId nodeId, GenericDirectedGraphBuilder<N,E,SO,NB,GenericDirectedGraph<N,E,SO>> graphBuilder) {
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
	public GenericDirectedGraph<N,E,SO> parentGraph(){
		return getGraphBuilder().asGraph();
	}

	@SuppressWarnings("unchecked")
	protected GenericDirectedGraphBuilder<N,E,SO,NB,?> graphBuilder(){
		return ((GenericDirectedGraphBuilderNtro<N,E,SO,NB,?>) parentGraph());
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
		return getGraphBuilder().asGraph().defaultSearchOptions();
	}

	@Override
	public boolean isStartNode() {
		return getIsStartNode();
	}
}
