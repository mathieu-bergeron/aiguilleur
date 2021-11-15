package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.Node;

public class GraphNtro<N extends Node, E extends Edge> extends GenericGraphNtro<GraphSearchOptions,N,E> implements Graph<N,E> {

	@Override
	protected GraphSearchOptions defaultSearchOptions() {
		return new GraphSearchOptions();
	}

	@Override
	protected void detectCycleFrom(N from) {
	}
}
