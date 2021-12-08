package ca.ntro.core.graphs.generics.graph;

public interface VisitedNode<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> 

       extends VisitedItem<N,E,SO> {
	
	N node();

}
