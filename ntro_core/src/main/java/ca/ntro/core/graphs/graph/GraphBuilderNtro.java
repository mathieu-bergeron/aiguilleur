package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      GraphBuilderNtro<N extends Node<N,E,SO>,
								   E extends Edge<N,E,SO>,
								   SO extends SearchOptions>

       extends    GenericGraphBuilderNtro<N,E,SO, Graph<N,E,SO>> 

       implements Graph<N,E,SO>, GraphBuilder<N,E,SO> {


	public GraphBuilderNtro() {
		super();
	}

	public GraphBuilderNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected GraphStructure<NV,EV,N,E> createGraphStructure() {
		return new GraphStructureNtro<NV,EV,N,E>();
	}

	@Override
	protected SearchOptionsNtro defaultSearchOptions() {
		return new SearchOptionsNtro();
	}

	@Override
	protected void detectCycleFrom(N from) {
	}

	@Override
	protected InternalGraphWriter<NV,EV,N,E> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}

}
