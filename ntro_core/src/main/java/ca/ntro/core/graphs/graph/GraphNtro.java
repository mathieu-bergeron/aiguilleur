package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilderNtro;

public class GraphNtro<N extends GraphNode<N,E>,
                       E extends GraphEdge<N,E>> 

       extends GenericGraphNtro<N,E,GraphSearchOptionsBuilder>

	   implements Graph<N,E> {

	public GraphNtro(GraphId id, GenericGraphStructure<N, E, GraphSearchOptionsBuilder> graphStructure) {
		super(id, graphStructure);
	}

	@Override
	public GraphSearchOptionsBuilder defaultSearchOptions() {
		return new GraphSearchOptionsBuilderNtro();
	}
}
