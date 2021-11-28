package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface GenericGraphBuilder<NV extends NodeValue, 
                                     EV extends EdgeValue, 
                                     N extends Node<NV>,
                                     E extends Edge<EV>,
                                     GS extends GenericGraphStructure<NV,EV,N,E>,
                                     G extends GenericGraph<NV,EV,N,E>> {

	N addNode(NV nodeValue);

	E addEdge(N from, EV edgeValue, N to);
	
	G toGraph();

}
