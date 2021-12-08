package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;

public interface InternalHierarchicalGraphWriter<N extends GenericHierarchicalNode<N,E,SO>,
 												 E extends GenericEdge<N,E,SO>,
 												 SO extends HierarchicalSearchOptions>

       extends InternalGraphWriter<N,E,SO> {

}
