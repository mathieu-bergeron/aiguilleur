package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

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
	protected SO defaultSearchOptions() {
		return (SO) new SearchOptionsNtro();
	}

	@Override
	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}

}
