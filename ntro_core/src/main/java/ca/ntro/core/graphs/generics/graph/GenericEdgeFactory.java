package ca.ntro.core.graphs.generics.graph;

public interface GenericEdgeFactory<N extends GenericNode<N,E,SO>,
                                    E extends GenericEdge<N,E,SO>,
                                    SO extends SearchOptions> {
	
	E newInstance();

}
