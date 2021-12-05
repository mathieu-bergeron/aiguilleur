package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;

public interface GenericNodeBuilder<N extends Node<N,E,SO>,
                                    E extends Edge<N,E,SO>,
                                    SO extends SearchOptionsBuilder> 

        extends Node<N,E,SO>,
                GenericNodeStructure<N,E,SO> {

	void setIsStartNode(boolean b);

	E addEdge(String edgeName, N toNode);

	N toNode();


}
