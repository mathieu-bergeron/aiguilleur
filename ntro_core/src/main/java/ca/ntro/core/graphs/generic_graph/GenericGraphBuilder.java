package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.SearchOptionsBuilder;

public interface GenericGraphBuilder<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder,
									 NB extends GenericNodeBuilder<N,E,SO,NB>,
                                     G extends GenericGraph<N,E,SO>> 

       extends GenericGraphStructure<N,E,SO> { 

	NB addNode(String nodeId);
	NB addNode(NodeId nodeId);

	E addEdge(NB fromNode, String edgeName, NB toNode);

	G asGraph();
}
