package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;

public abstract class HierarchicalGraphBuilderNtro<N extends HierarchicalNode<N,E,SO>,
											   E extends Edge<N,E,SO>,
											   SO extends HierarchicalGraphSearchOptionsBuilder,
											   NB extends HierarchicalNodeBuilder<N,E,SO,NB>,
											   G extends HierarchicalGraph<N,E,SO>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,G> 

       implements     HierarchicalGraphBuilder<N,E,SO,NB,G> {

	public HierarchicalGraphBuilderNtro() {
		super();
	}

	public HierarchicalGraphBuilderNtro(String graphName) {
		super(graphName);
	}

	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}
}
