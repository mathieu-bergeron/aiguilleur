package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilder;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeBuilder;

public interface HierarchicalDagBuilder<N extends GenericHierarchicalNode<N,E,SO>,
                                        E extends GenericEdge<N,E,SO>,
                                        SO extends HierarchicalDagSearchOptionsBuilder,
                                        NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
                                        G extends HierarchicalDag<N,E,SO>>

       extends   GenericHierarchicalGraphBuilder<N,E,SO,NB,G> {

}
