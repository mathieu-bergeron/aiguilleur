package ca.ntro.core.graphs.directed_graph;


import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      DirectedGraphNtro<N extends Node<N,E,SO>,
                                    E extends Edge<N,E,SO>,
                                    SO extends SearchOptionsNtro>

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
	protected SO defaultSearchOptions() {
		return (SO) new DirectedGraphSearchOptions();
	}

	@Override
	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}
}
