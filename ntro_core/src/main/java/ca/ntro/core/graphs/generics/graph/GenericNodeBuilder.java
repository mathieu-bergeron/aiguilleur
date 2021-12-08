package ca.ntro.core.graphs.generics.graph;

public interface GenericNodeBuilder<N extends GenericNode<N,E,SO>,
                                    E extends GenericEdge<N,E,SO>,
                                    SO extends SearchOptions,
                                    NB extends GenericNodeBuilder<N,E,SO,NB>> 

        extends  GenericNodeStructure<N,E,SO> {
	
	E addEdge(String edgeName, NB toNode);

}
