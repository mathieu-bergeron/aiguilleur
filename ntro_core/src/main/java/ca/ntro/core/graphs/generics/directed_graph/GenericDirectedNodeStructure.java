package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;

public interface GenericDirectedNodeStructure <N extends GenericDirectedNode<N,E,SO>, 
                                               E extends GenericDirectedEdge<N,E,SO>,
                                               SO extends DirectedGraphSearchOptions>

       extends   GenericNodeStructure<N,E,SO> {

}
