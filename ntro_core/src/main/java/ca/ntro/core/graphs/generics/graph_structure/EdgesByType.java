package ca.ntro.core.graphs.generics.graph_structure;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;

public interface EdgesByType<N extends GenericNode<N,E,SO>, 
                                 E extends GenericEdge<N,E,SO>,
                                 SO extends SearchOptions> 

       extends EdgesMap<N,E,SO> {


}
