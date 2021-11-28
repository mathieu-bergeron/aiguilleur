package ca.ntro.core.graphs.directed_graph;


import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      DirectedGraphNtro<N extends Node<N,E,SO>,
                                    E extends Edge<N,E,SO>,
                                    SO extends SearchOptions>

       extends    GenericGraphBuilderNtro<N,E,SO,DirectedGraph<N,E,SO>> 

       implements DirectedGraph<N,E,SO>, 
                  DirectedGraphBuilder<N,E,SO> {

	public DirectedGraphNtro() {
		super();
	}

	public DirectedGraphNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions();
	}

	@Override
	protected DirectedGraphStructure<NV,EV,N,E> createGraphStructure() {
		return new DirectedGraphStructureNtro<>();
	}

	@Override
	protected void detectCycleFrom(N from) {
	}

	@Override
	protected InternalGraphWriter<NV,EV,N,E> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}
}
