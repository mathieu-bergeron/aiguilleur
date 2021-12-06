package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNode;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeBuilder;

public abstract class HierarchicalDagBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                 E extends Edge<N,E,SO>,
                                                 SO extends HierarchicalDagSearchOptionsBuilder,
                                                 NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
                                                 G extends HierarchicalDag<N,E,SO>>

       extends GenericGraphBuilderNtro<N,E,SO,NB,G>

	   implements HierarchicalDagBuilder<N,E,SO,NB,G> {


}
