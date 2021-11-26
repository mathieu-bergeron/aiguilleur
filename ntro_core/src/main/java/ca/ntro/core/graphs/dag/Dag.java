package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.directed_graph.DirectedGraph;

public interface Dag<NV extends NodeValue,
                     EV extends EdgeValue,
                     N extends Node<NV>,
                     E extends Edge<EV>> 

       extends   DirectedGraph<NV,EV,N,E> {

}
