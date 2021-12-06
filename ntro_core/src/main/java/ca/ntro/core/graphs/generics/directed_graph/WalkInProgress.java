package ca.ntro.core.graphs.generics.directed_graph;

public interface WalkInProgress<N extends GenericNode<N,E,SO>, 
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptionsBuilder> 

       extends VisitedItem<N,E,SO> {

	WalkId remainingWalk();
	N currentNode();

}
