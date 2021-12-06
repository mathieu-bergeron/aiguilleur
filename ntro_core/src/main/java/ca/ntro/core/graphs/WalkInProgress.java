package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.WalkId;

public interface WalkInProgress<N extends Node<N,E,SO>, 
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptionsBuilder> 

       extends ReachedItem<N,E,SO> {

	WalkId remainingWalk();
	N currentNode();

}
