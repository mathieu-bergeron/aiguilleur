package ca.ntro.core.graphs.generics.directed_graph;

public interface GenericNodeBuilder<N extends GenericNode<N,E,SO>,
                                    E extends Edge<N,E,SO>,
                                    SO extends SearchOptionsBuilder,
                                    NB extends GenericNodeBuilder<N,E,SO,NB>> 

        extends  GenericNode<N,E,SO>,
                 GenericNodeStructure<N,E,SO> {

	void setIsStartNode(boolean b);

	E addEdge(String edgeName, NB toNode);
	void addEdge(E edge);

	N toNode();


}