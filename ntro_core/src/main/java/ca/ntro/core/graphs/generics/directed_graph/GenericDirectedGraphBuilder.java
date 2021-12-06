package ca.ntro.core.graphs.generics.directed_graph;

public interface GenericDirectedGraphBuilder<N extends GenericNode<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder,
									 NB extends GenericNodeBuilder<N,E,SO,NB>,
                                     G extends GenericDirectedGraph<N,E,SO>> 

       extends GenericGraphStructure<N,E,SO> { 

	NB addNode(String nodeId);
	NB addNode(NodeId nodeId);

	E addEdge(NB fromNode, String edgeName, NB toNode);

	G asGraph();
}
