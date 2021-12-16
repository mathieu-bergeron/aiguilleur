package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;

public abstract class GenericHierarchicalGraphNtro <N extends GenericHierarchicalNode<N,E,SO>,
								                    E extends GenericEdge<N,E,SO>,
								                    SO extends HierarchicalSearchOptions,
								                    GO extends HierarchicalGraphWriterOptions> 

       extends GenericGraphNtro<N,E,SO,GO> 

       implements GenericHierarchicalGraph<N,E,SO,GO>{

	@Override
	protected GenericInternalGraphWriter<N,E,SO,GO> newInternalGraphWriterInstance() {
		return new GenericInternalHierarchicalGraphWriterNtro<>();
	}
}
