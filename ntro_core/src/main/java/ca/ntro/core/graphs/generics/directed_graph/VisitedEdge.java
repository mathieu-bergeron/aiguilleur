package ca.ntro.core.graphs.generics.directed_graph;


public interface VisitedEdge<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       extends VisitedItem<N,E,SO> {

	E edge();

}
