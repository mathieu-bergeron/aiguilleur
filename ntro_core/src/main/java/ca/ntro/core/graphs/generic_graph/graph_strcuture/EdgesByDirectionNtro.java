package ca.ntro.core.graphs.generic_graph.graph_strcuture;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;

public class      EdgesByDirectionNtro<N extends Node<N,E,SO>, 
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptionsBuilder,
                                       SUBMAP extends EdgesByType<N,E,SO>> 

       extends    EdgesMapNtro<N,E,SO, SUBMAP>

       implements EdgesByDirection<N,E,SO> {

	@Override
	protected SUBMAP createSubMap() {
		return (SUBMAP) new EdgesByTypeNtro<N,E,SO, EdgesByToId<N,E,SO>>();
	}

	@Override
	protected Collection<SUBMAP> subMapsForDirection(Direction direction) {
		List<SUBMAP> subMaps = new ArrayList<>();
		subMaps.add(getEdgesMap().get(direction.name()));

		return subMaps;
	}

	@Override
	protected String getSubMapKey(E edge) {
		return edge.type().direction().name();
	}

	@Override
	protected String getSubMapKey(EdgeType edgeName) {
		return edgeName.direction().name();
	}




}
