package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;

public interface DirectedGraph<N extends  DirectedNode<N,E>, 
                               E extends  DirectedEdge<N,E>>

       extends   GenericDirectedGraph<N,E,DirectedGraphSearchOptions,DirectedGraphWriterOptions> {

}
