package ca.ntro.core.graphs.generics.generic_graph;

public interface Walk<N extends Node<N,E,SO>,
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder>

       extends GenericWalk<E> {


}
