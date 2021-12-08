package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraph;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;

public interface HierarchicalDag<N extends GenericHierarchicalNode<N,E,SO>,
                                 E extends GenericEdge<N,E,SO>,
                                 SO extends HierarchicalDagSearchOptionsBuilder>

                                 extends GenericHierarchicalGraph<N,E,SO> {


}
