package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public class EdgesByToIdNtro<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptions> 

       implements EdgesByToId<N,E,SO> {
	

	private Map<String, E> edges = new HashMap<>();

	public Map<String, E> getEdges() {
		return edges;
	}

	public void setEdges(Map<String, E> edges) {
		this.edges = edges;
	}

	@Override
	public void addEdge(E edge) {
		getEdges().put(edge.to().id().toKey().toString(), edge);
	}


}
