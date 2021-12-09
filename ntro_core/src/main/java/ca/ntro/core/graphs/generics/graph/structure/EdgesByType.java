package ca.ntro.core.graphs.generics.graph.structure;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;

public interface EdgesByType<N extends GenericNode<N,E,SO>, 
                                 E extends GenericEdge<N,E,SO>,
                                 SO extends SearchOptions> 

       extends EdgesMap<N,E,SO> {


}
