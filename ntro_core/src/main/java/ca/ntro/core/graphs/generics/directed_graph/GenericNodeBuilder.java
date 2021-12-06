package ca.ntro.core.graphs.generics.directed_graph;

public interface GenericNodeBuilder<N extends GenericNode<N,E,SO>,
                                    E extends Edge<N,E,SO>,
                                    SO extends SearchOptionsBuilder,
                                    NB extends GenericNodeBuilder<N,E,SO,NB>> 

        extends  GenericNodeStructure<N,E,SO> {
	
	
	void setNodeName(String nodeName);

	void setIsStartNode(boolean b);

	E addEdge(String edgeName, NB toNode);

	void addEdge(E edge);

}
