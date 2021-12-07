package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeBuilder;

public interface GenericHierarchicalNodeBuilder<N  extends GenericHierarchicalNode<N,E,SO>,
                                         E  extends Edge<N,E,SO>,
                                         SO extends HierarchicalSearchOptionsBuilder,
                                         NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>>

       extends   GenericNodeBuilder<N,E,SO,NB> {

	void addSubNode(NB subNode);

}
