package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.SearchOptionsBuilder;

public interface GenericGraphBuilder<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder,
                                     G extends GenericGraph<N,E,SO>> {

	N addNode(String nodeId);
	N addNode(NodeId nodeId);

	E addEdge(N fromNode, String edgeName, N toNode);

	G toGraph();
}
