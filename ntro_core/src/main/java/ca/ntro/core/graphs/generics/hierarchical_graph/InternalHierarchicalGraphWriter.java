package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;

public interface InternalHierarchicalGraphWriter<N extends GenericHierarchicalNode<N,E,SO>,
 												 E extends GenericEdge<N,E,SO>,
 												 SO extends HierarchicalSearchOptions,
 												 GO extends HierarchicalGraphWriterOptions>

       extends GenericInternalGraphWriter<N,E,SO,GO> {

}
