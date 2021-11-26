package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      GraphBuilderNtro<NV extends NodeValue, 
                                   EV extends EdgeValue,
                                   N extends Node<NV>,
                                   E extends Edge<EV>> 

       extends    GenericGraphBuilderNtro<NV,EV,N,E,GraphStructure<NV,EV,N,E>,Graph<NV,EV,N,E>> 

       implements Graph<NV,EV,N,E>, GraphBuilder<NV,EV,N,E> {


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
