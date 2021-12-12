package ca.ntro.core.graphs.generics.graph;

public interface Walk<N extends GenericNode<N,E,SO>,
                      E extends GenericEdge<N,E,SO>,
                      SO extends SearchOptions>

       extends GenericWalk<E, Walk<N,E,SO>> {


}
