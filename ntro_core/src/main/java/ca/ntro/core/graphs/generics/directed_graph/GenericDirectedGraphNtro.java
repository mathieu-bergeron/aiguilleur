package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;

public class      GenericDirectedGraphNtro<N extends  GenericDirectedNode<N,E,SO>, 
                                           E extends  GenericDirectedEdge<N,E,SO>,
                                           SO extends DirectedSearchOptions> 

       extends    GenericGraphNtro<N,E,SO>


       implements GenericDirectedGraph<N,E,SO> {

	@Override
	protected InternalGraphWriter<N, E, SO> newInternalGraphWriterInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SO newDefaultSearchOptionsInstance() {
		return (SO) new DirectedSearchOptionsNtro();
	}
}
