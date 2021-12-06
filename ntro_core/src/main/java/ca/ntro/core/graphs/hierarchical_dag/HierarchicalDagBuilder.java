package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphBuilder;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNode;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNodeBuilder;

public interface HierarchicalDagBuilder<N extends HierarchicalNode<N,E,SO>,
                                        E extends Edge<N,E,SO>,
                                        SO extends HierarchicalDagSearchOptionsBuilder,
                                        NB extends HierarchicalNodeBuilder<N,E,SO,NB>,
                                        G extends HierarchicalDag<N,E,SO>>

       extends   HierarchicalGraphBuilder<N,E,SO,NB,G> {

}
