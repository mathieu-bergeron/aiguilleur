package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.NodeId;

public interface GenericGraphBuilder<N extends GenericNode<N,E,SO>,
                                     E extends GenericEdge<N,E,SO>,
                                     SO extends SearchOptions,
									 NB extends GenericNodeBuilder<N,E,SO,NB>,
									 GO extends GraphWriterOptions,
                                     G extends GenericGraph<N,E,SO,GO>> 

       extends GenericGraphStructure<N,E,SO> { 
	
	NB addNode(String nodeId);
	NB addNode(NodeId nodeId);
	NB addNode(N node);

	NB findNode(String nodeId);
	NB findNode(NodeId nodeId);

	E addEdge(NB fromNode, String edgeName, NB toNode);

	G graph();

	void setGraphName(String string);
}
