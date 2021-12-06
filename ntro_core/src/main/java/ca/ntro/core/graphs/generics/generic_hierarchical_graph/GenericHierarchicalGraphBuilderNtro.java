package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;

public abstract class GenericHierarchicalGraphBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
											   E extends Edge<N,E,SO>,
											   SO extends HierarchicalGraphSearchOptionsBuilder,
											   NB extends GenericHierarchicalNodeBuilderNtro<N,E,SO,NB>,
											   G extends GenericHierarchicalGraphNtro<N,E,SO>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,G> 

       implements     GenericHierarchicalGraphBuilder<N,E,SO,NB,G> {


	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}
}
