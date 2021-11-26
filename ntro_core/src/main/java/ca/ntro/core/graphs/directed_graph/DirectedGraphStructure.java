package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.graph.GraphStructure;

public interface DirectedGraphStructure<NV extends NodeValue, 
                                        EV extends EdgeValue,
                                        N extends Node<NV>,
                                        E extends Edge<EV>> extends GraphStructure<NV,EV,N,E> {

}
