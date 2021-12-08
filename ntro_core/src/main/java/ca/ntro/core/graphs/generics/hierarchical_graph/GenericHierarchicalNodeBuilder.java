package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;

public interface GenericHierarchicalNodeBuilder<N  extends GenericHierarchicalNode<N,E,SO>,
                                         E  extends GenericEdge<N,E,SO>,
                                         SO extends HierarchicalSearchOptions,
                                         NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>>

       extends   GenericNodeBuilder<N,E,SO,NB> {

	void addSubNode(NB subNode);

}
