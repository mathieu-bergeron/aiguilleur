package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;

public class GenericHierarchicalGraphNtro <N extends GenericHierarchicalNode<N,E,SO>,
								           E extends Edge<N,E,SO>,
								           SO extends HierarchicalSearchOptionsBuilder> 

       extends GenericGraphNtro<N,E,SO> 

       implements GenericHierarchicalGraph<N,E,SO>{

	@Override
	protected InternalGraphWriter<N, E, SO> newInternalGraphWriterInstance() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}

	@Override
	protected SO newDefaultSearchOptionsInstance() {
		return (SO) new HierarchicalSearchOptionsBuilderNtro();
	}
}
