package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;

public abstract class HierarchicalGraphBuilderNtro<N extends HierarchicalNode<N,E,SO>,
											   E extends Edge<N,E,SO>,
											   SO extends HierarchicalGraphSearchOptionsBuilder,
											   NB extends HierarchicalNodeBuilder<N,E,SO,NB>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,HierarchicalGraph<N,E,SO>> 

       implements     HierarchicalGraph<N,E,SO>, HierarchicalGraphBuilder<N,E,SO,NB> {

	public HierarchicalGraphBuilderNtro() {
		super();
	}

	public HierarchicalGraphBuilderNtro(String graphName) {
		super(graphName);
	}

	protected InternalGraphWriter<N,E,SO> internalGraphWriter() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}

	@Override
	public SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}
}
