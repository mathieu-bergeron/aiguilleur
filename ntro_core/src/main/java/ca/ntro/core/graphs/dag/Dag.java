package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraph;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface Dag<N extends GenericNode<N,E,SO>,
                     E extends GenericEdge<N,E,SO>,
                     SO extends DagSearchOptionsBuilder>

       extends   DirectedGraph<N,E,SO> {

}
