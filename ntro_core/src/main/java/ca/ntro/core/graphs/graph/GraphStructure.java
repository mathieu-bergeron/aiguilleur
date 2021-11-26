package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;

public interface GraphStructure<NV extends NodeValue, 
                                EV extends EdgeValue,
                                N extends Node<NV>,
                                E extends Edge<EV>> extends GenericGraphStructure<NV,EV,N,E> {

}
