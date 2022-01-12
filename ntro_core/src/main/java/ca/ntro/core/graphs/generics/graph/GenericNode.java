package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.stream.Stream;


public interface GenericNode<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> {

	NodeId id();
	String label();

	GenericGraph<N,E,SO,?> parentGraph();

	boolean isStartNode();
	boolean isPartOfCycle();
	
	Stream<E> edges();
	Stream<E> edges(SO options);

	Stream<VisitedNode<N,E,SO>> reachableNodes();
	Stream<VisitedNode<N,E,SO>> reachableNodes(SO options);

	Stream<VisitedEdge<N,E,SO>> reachableEdges();
	Stream<VisitedEdge<N,E,SO>> reachableEdges(SO options);

	Stream<WalkInProgress<N,E,SO>> walk(WalkId walk);
	Stream<WalkInProgress<N,E,SO>> walk(WalkId walk, SO options);
	
	boolean equals(Object o);

}
