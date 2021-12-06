package ca.ntro.core.graphs.generics.directed_graph;

public interface VisitedItem<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {

	Walk<N,E,SO> walked();

}
