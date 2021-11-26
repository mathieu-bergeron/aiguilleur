package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;

public interface GenericGraphBuilder<NV extends NodeValue, 
                                     EV extends EdgeValue, 
                                     N extends Node<NV>,
                                     E extends Edge<EV>,
                                     GS extends GenericGraphStructure<NV,EV,N,E>,
                                     G extends GenericGraph<NV,EV,N,E>> {

	Node<NV> addNode(NV nodeValue);

	Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to);
	
	G toGraph();

}
