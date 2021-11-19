package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface GenericGraphBuilder<NV extends NodeValue, 
                                     EV extends EdgeValue, 
                                     GS extends GenericGraphStructure<NV,EV>,
                                     G extends GenericGraph<NV,EV>> {

	Node<NV> addNode(NV nodeValue);

	Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to);
	
	G toGraph();

}
