package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      GraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphBuilderNtro<NV,EV,GraphStructure<NV,EV>,Graph<NV,EV>> 
       implements Graph<NV,EV>, GraphBuilder<NV,EV> {


	public GraphNtro() {
		super();
	}

	public GraphNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected GraphStructure<NV, EV> createGraphStructure() {
		return new GraphStructureNtro<NV,EV>();
	}

	@Override
	protected SearchOptionsNtro defaultSearchOptions() {
		return new SearchOptionsNtro();
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}

	@Override
	protected InternalGraphWriter<NV, EV> internalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}

}
