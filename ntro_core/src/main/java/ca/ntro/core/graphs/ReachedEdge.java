package ca.ntro.core.graphs;


public interface ReachedEdge<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       extends ReachedItem<N,E,SO> {

	E edge();

}
