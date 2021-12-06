package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.graph_structure.EdgesByDirection;
import ca.ntro.core.graphs.generics.directed_graph.graph_structure.EdgesByDirectionNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class GenericNodeStructureBuilderNtro<N extends GenericNode<N,E,SO>, 
                                             E extends Edge<N,E,SO>,
                                             SO extends SearchOptionsBuilder> 

       implements GenericNodeStructureBuilder<N,E,SO> {
	
	private boolean isStartNode = false;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();
	
	public boolean getIsStartNode() {
		return isStartNode;
	}
	
	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
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
	public void addEdge(E edge) {
		if(getEdgesByDirection().containsEdge(edge)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("Edge already added: " +  edge.id().toKey()));
			
		}else {

			getEdgesByDirection().addEdge(edge);
		}
	}

	public boolean isStartNode() {
		return getIsStartNode();
	}

	@Override
	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}


}
