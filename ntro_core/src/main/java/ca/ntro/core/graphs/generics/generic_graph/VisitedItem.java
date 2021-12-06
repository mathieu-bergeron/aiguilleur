package ca.ntro.core.graphs.generics.generic_graph;

public interface VisitedItem<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {

	Walk<N,E,SO> walked();

}
