package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.stream.Stream;

public interface GenericHierarchicalNode<N extends GenericHierarchicalNode<N,E,SO>,
                                         E extends GenericEdge<N,E,SO>,
                                         SO extends HierarchicalSearchOptions>

       extends   GenericNode<N,E,SO> {
	
	boolean hasSubNodes();
	boolean hasParent();

	N parent();
	
	Stream<VisitedNode<N,E,SO>> subNodes();
	Stream<VisitedNode<N,E,SO>> subNodes(SO options);

	Stream<VisitedNode<N,E,SO>> parentNodes();
	Stream<VisitedNode<N,E,SO>> parentNodes(SO options);
}
