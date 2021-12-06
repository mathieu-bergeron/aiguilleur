package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;

public abstract class GenericHierarchicalGraphBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
											   E extends Edge<N,E,SO>,
											   SO extends HierarchicalGraphSearchOptionsBuilder,
											   NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
											   G extends GenericHierarchicalGraph<N,E,SO>>

       extends        GenericDirectedGraphBuilderNtro<N,E,SO,NB,G> 

       implements     GenericHierarchicalGraphBuilder<N,E,SO,NB,G> {

	public GenericHierarchicalGraphBuilderNtro() {
		super();
	}

	public GenericHierarchicalGraphBuilderNtro(String graphName) {
		super(graphName);
	}

	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}
}
