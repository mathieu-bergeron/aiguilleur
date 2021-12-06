package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.directed_graph.SearchOptionsNtro;

public interface InternalHierarchicalGraphWriter<N extends HierarchicalNode<N,E,SO>,
 												 E extends Edge<N,E,SO>,
 												 SO extends HierarchicalGraphSearchOptionsBuilder>

       extends InternalGraphWriter<N,E,SO> {

}
