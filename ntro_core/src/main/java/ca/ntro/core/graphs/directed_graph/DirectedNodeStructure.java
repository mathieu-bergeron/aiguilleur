package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;

public interface DirectedNodeStructure<N extends  DirectedNode<N,E>, 
                                       E extends  DirectedEdge<N,E>>

       extends   GenericNodeStructure<N,E,DirectedGraphSearchOptions> {

}
