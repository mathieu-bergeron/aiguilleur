package ca.ntro.core.graphs.generic_graph;

public interface GenericGraphBuilder<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<SO,NV,EV>> {

	Node<NV> addNode(NV nodeValue);

	Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to);
	
	G toGraph();

}
