package ca.ntro.core.graphs.generics.generic_graph.graph_structure;


import java.util.Collection;
import java.util.Set;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.EdgeType;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsBuilder;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;

public class   EdgesByTypeNtro <N extends Node<N,E,SO>, 
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptionsBuilder,
                                SUBMAP extends EdgesByToId<N,E,SO>> 

       extends EdgesMapNtro<N,E,SO,SUBMAP>

       implements EdgesByType<N,E,SO> {

	@Override
	protected SUBMAP createSubMap() {
		return (SUBMAP) new EdgesByToIdNtro<N,E,SO>();
	}

	@Override
	protected Collection<SUBMAP> subMapsForDirection(Direction direction) {
		return getEdgesMap().values();
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