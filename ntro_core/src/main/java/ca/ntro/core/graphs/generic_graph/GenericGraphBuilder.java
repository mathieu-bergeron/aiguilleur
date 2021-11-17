package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.SearchOptions;

public interface GenericGraphBuilder<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<SO,NV,EV>> {

	Node<NV> addNode(NV nodeValue);

	Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to);
	
	G toGraph();

}
