package ca.ntro.core.graphs.generics.graph;

public interface GenericNodeFactory<N extends GenericNode<N,E,SO>,
                                    E extends GenericEdge<N,E,SO>,
                                    SO extends SearchOptions> {

	N newInstance();

}
