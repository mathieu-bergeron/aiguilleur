package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;

public class GraphNtro<N extends GraphNode<N,E>,
                       E extends GraphEdge<N,E>> 

       extends GenericDirectedGraphNtro<N,E,GraphSearchOptionsBuilder>

	   implements Graph<N,E> {


	@Override
	public GraphSearchOptionsBuilder defaultSearchOptions() {
		return new GraphSearchOptionsBuilderNtro();
	}

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalGraphWriter<N, E, GraphSearchOptionsBuilder> internalGraphWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericGraphStructure<N, E, GraphSearchOptionsBuilder> graphStructure() {
		// TODO Auto-generated method stub
		return null;
	}
}
