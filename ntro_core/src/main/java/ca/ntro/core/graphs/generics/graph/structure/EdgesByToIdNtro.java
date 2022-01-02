package ca.ntro.core.graphs.generics.graph.structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class EdgesByToIdNtro<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> 

       implements EdgesByToId<N,E,SO> {

	

	private Map<String, E> edgesMap = new HashMap<>();

	public Map<String, E> getEdgesMap() {
		return edgesMap;
	}

	public void setEdgesMap(Map<String, E> edgesMap) {
		this.edgesMap = edgesMap;
	}

	@Override
	public boolean containsEdge(E edge) {
		return getEdgesMap().containsKey(edge.to().id().toKey().toString());
	}

	@Override
	public void addEdge(E edge) {
		getEdgesMap().put(edge.to().id().toKey().toString(), edge);
	}

	@Override
	public Stream<EdgeType> edgeTypes(Direction direction) {
		return new StreamNtro<EdgeType>() {

			@Override
			public void forEach_(Visitor<EdgeType> visitor) throws Throwable {

				for(E edge: edgesMap.values()) {

					visitor.visit(edge.type());
				}
			}
		};
	}

	@Override
	public Stream<E> edges(EdgeType edgeType) {
		return new StreamNtro<E>() {

			@Override
			public void forEach_(Visitor<E> visitor) throws Throwable {
				for(E edge: edgesMap.values()) {

					visitor.visit(edge);
				}
			}
		};
	}
}
