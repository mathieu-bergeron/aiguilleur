package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraph;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNode;

public interface HierarchicalDag<N extends HierarchicalNode<N,E,SO>,
                                 E extends Edge<N,E,SO>,
                                 SO extends HierarchicalDagSearchOptionsBuilder>

                                 extends HierarchicalGraph<N,E,SO> {


}
