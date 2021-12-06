package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;

public interface GenericHierarchicalNodeBuilder<N  extends GenericHierarchicalNode<N,E,SO>,
                                         E  extends Edge<N,E,SO>,
                                         SO extends HierarchicalGraphSearchOptionsBuilder,
                                         NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>>

       extends   GenericNodeBuilder<N,E,SO,NB> {

	void addSubNode(NB subNode);

}
