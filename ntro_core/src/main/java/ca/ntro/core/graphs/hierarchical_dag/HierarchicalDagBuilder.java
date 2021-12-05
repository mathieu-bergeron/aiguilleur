package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface HierarchicalDagBuilder<N extends HierarchicalNode<N,E,SO>,
                                        E extends Edge<N,E,SO>,
                                        SO extends HierarchicalDagSearchOptionsBuilder,
                                        NB extends GenericNodeBuilder<N,E,SO,NB>,
                                        G extends HierarchicalDag<N,E,SO>>

       extends   HierarchicalDag<N,E,SO>,
       			 GenericGraphBuilder<N,E,SO,NB,G> {

}
