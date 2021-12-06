package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalGraph;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNode;

public interface HierarchicalDag<N extends GenericHierarchicalNode<N,E,SO>,
                                 E extends Edge<N,E,SO>,
                                 SO extends HierarchicalDagSearchOptionsBuilder>

                                 extends GenericHierarchicalGraph<N,E,SO> {


}
