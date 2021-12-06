package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;

public interface Dag<N extends GenericNode<N,E,SO>,
                     E extends Edge<N,E,SO>,
                     SO extends DagSearchOptionsBuilder>

       extends   DirectedGraph<N,E,SO> {

}
