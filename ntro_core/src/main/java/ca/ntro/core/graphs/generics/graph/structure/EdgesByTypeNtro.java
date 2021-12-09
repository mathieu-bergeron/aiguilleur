package ca.ntro.core.graphs.generics.graph.structure;


import java.util.Collection;
import java.util.Set;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;

public class   EdgesByTypeNtro <N extends GenericNode<N,E,SO>, 
                                E extends GenericEdge<N,E,SO>,
                                SO extends SearchOptions,
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