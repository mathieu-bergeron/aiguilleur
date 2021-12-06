package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;

public interface InternalHierarchicalGraphWriter<N extends GenericHierarchicalNode<N,E,SO>,
 												 E extends Edge<N,E,SO>,
 												 SO extends HierarchicalGraphSearchOptionsBuilder>

       extends InternalGraphWriter<N,E,SO> {

}
