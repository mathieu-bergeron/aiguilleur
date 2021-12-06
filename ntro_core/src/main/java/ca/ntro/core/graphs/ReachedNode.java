package ca.ntro.core.graphs;

public interface ReachedNode<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       extends ReachedItem<N,E,SO> {
	
	N node();

}
