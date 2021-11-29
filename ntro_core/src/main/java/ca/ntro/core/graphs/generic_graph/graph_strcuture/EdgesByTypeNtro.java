package ca.ntro.core.graphs.generic_graph.graph_strcuture;


import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public class   EdgesByTypeNtro <N extends Node<N,E,SO>, 
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptions,
                                SUBMAP extends EdgesByToId<N,E,SO>> 

       extends EdgesMapNtro<N,E,SO,SUBMAP>

       implements EdgesByType<N,E,SO> {

	@Override
	protected SUBMAP createSubMap() {
		return (SUBMAP) new EdgesByToIdNtro<N,E,SO>();
	}

	@Override
	protected String getSubMapKey(E edge) {
		return edge.type().name().toString();
	}

	@Override
	protected String getSubMapKey(EdgeType edgeName) {
		return edgeName.name().toString();
	}
}
