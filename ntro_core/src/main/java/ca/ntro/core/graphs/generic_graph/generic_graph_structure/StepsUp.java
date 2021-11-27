package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface  StepsUp<NV extends NodeValue,
                          EV extends EdgeValue,
                          N extends Node<NV>,
                          E extends Edge<EV>>

       extends    StepsUpDown<NV,EV,N,E> {

}
