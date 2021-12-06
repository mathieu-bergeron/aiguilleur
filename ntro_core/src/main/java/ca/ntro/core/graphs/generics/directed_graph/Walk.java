package ca.ntro.core.graphs.generics.directed_graph;

public interface Walk<N extends GenericNode<N,E,SO>,
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder>

       extends GenericWalk<E> {


}
