package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraph;

public interface Graph<NV extends NodeValue, 
                       EV extends EdgeValue,
                       N extends Node<NV>,
                       E extends Edge<EV>> 

       extends    GenericGraph<NV,EV,N,E> {
	

}
