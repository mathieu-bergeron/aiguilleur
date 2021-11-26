package ca.ntro.core.graphs.directed_graph;


import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;

public class      DirectedGraphNtro<NV extends NodeValue, 
                                    EV extends EdgeValue,
                                    N extends Node<NV>,
                                    E extends Edge<EV>> 

       extends    GenericGraphBuilderNtro<NV,EV,N,E,DirectedGraphStructure<NV,EV,N,E>,
                  DirectedGraph<NV,EV,N,E>> 

       implements DirectedGraph<NV,EV,N,E>, 
                  DirectedGraphBuilder<NV,EV,N,E> {

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
