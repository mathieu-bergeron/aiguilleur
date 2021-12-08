package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;

public class GenericHierarchicalGraphNtro <N extends GenericHierarchicalNode<N,E,SO>,
								           E extends GenericEdge<N,E,SO>,
								           SO extends HierarchicalSearchOptions> 

       extends GenericGraphNtro<N,E,SO> 

       implements GenericHierarchicalGraph<N,E,SO>{

	@Override
	protected InternalGraphWriter<N, E, SO> newInternalGraphWriterInstance() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}

	@Override
	protected SO newDefaultSearchOptionsInstance() {
		return (SO) new HierarchicalSearchOptionsNtro();
	}
}
