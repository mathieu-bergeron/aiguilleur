package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphStructure;

public interface GenericDirectedGraphStructure<N extends GenericDirectedNode<N,E,SO>, 
                                               E extends GenericDirectedEdge<N,E,SO>,
                                               SO extends DirectedGraphSearchOptions>

       extends   GenericGraphStructure<N,E,SO> {

}
