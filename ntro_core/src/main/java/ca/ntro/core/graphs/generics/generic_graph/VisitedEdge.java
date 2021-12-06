package ca.ntro.core.graphs.generics.generic_graph;


public interface VisitedEdge<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       extends VisitedItem<N,E,SO> {

	E edge();

}
