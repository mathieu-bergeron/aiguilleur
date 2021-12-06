package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalGraph;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalNode;

public interface HierarchicalDag<N extends GenericHierarchicalNode<N,E,SO>,
                                 E extends Edge<N,E,SO>,
                                 SO extends HierarchicalDagSearchOptionsBuilder>

                                 extends GenericHierarchicalGraph<N,E,SO> {


}
