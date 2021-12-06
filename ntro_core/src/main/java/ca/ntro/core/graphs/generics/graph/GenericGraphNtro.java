package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriterNtro;

public abstract class GenericGraphNtro<N extends GenericNode<N,E,SO>,
                                       E extends Edge<N,E,SO>,
                                       SO extends GraphSearchOptionsBuilder> 

       extends GenericDirectedGraphNtro<N,E,SO> 

       implements GenericGraph<N,E,SO> {
	
	
	private GraphId id;
	private GenericGraphStructure<N, E, SO> graphStructure;
	

	public GenericGraphNtro(GraphId id, GenericGraphStructure<N, E, SO> graphStructure) {
		this.id = id;
		this.graphStructure = graphStructure;
	}

	@Override
	public GraphId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toString();
	}

	@Override
	protected InternalGraphWriter<N, E, SO> internalGraphWriter() {
		return new InternalGraphWriterNtro<N,E,SO>();
	}

	@Override
	protected GenericGraphStructure<N, E, SO> graphStructure() {
		return graphStructure;
	}

}
