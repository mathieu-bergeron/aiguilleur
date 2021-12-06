package ca.ntro.core.graphs.generics.generic_graph;

public interface GenericGraphBuilder<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder,
									 NB extends GenericNodeBuilder<N,E,SO,NB>,
                                     G extends GenericGraph<N,E,SO>> 

       extends GenericGraphStructure<N,E,SO> { 
	
	void setNodeClass(Class<N> nodeClass);
	void setEdgeClass(Class<E> edgeClass);
	void setSearchOptionsClass(Class<SO> searchOptionsClass);

	NB addNode(String nodeId);
	NB addNode(NodeId nodeId);

	void addNode(N node);

	E addEdge(NB fromNode, String edgeName, NB toNode);

	G graph();

	void setGraphName(String string);
}
