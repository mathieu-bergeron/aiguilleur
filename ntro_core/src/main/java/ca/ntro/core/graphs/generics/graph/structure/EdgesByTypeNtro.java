package ca.ntro.core.graphs.generics.graph.structure;

import java.util.Collection;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;

public class   EdgesByTypeNtro <N extends GenericNode<N,E,SO>, 
                                E extends GenericEdge<N,E,SO>,
                                SO extends SearchOptions,
                                SUBMAP extends EdgesByToId<N,E,SO>> 

       extends EdgesMapNtro<N,E,SO,SUBMAP>

       implements EdgesByType<N,E,SO> {

	@SuppressWarnings("unchecked")
	@Override
	protected SUBMAP createSubMap() {
		
		// JSWeet: local variable before subtyping
		EdgesByToIdNtro<N, E, SO> subMap = new EdgesByToIdNtro<N,E,SO>();
		
		return (SUBMAP) subMap;
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
