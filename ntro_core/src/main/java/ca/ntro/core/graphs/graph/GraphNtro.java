package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriterNtro;

public class GraphNtro<N extends GraphNode<N,E>,
                       E extends GraphEdge<N,E>> 

       extends GenericGraphNtro<N,E,GraphSearchOptionsBuilder>

	   implements Graph<N,E> {
	

	private GraphBuilderNtro<N, E> graphBuilder;

	@Override
	public GraphSearchOptionsBuilder defaultSearchOptions() {
		return new GraphSearchOptionsBuilderNtro();
	}

	@Override
	public GraphId id() {
		return GraphId.newGraphId();
	}

	@Override
	public String label() {
		return GraphId.newGraphId().toKey().toString();
	}

	@Override
	public InternalGraphWriter<N, E, GraphSearchOptionsBuilder> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}

	@Override
	public GenericGraphStructure<N, E, GraphSearchOptionsBuilder> graphStructure() {
		return this.graphBuilder;
	}

	public void setGraphStructure(GraphBuilderNtro<N, E> graphBuilderNtro) {
		this.graphBuilder = graphBuilderNtro;
	}
}
