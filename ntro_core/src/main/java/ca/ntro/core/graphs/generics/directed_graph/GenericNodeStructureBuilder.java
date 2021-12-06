package ca.ntro.core.graphs.generics.directed_graph;

public interface GenericNodeStructureBuilder<N extends GenericNode<N,E,SO>, 
                                             E extends Edge<N,E,SO>,
                                             SO extends SearchOptionsBuilder>

       extends GenericNodeStructure<N,E,SO> {

	void setIsStartNode(boolean b);

	void addEdge(E edge);

}
