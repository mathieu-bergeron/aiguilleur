package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraph;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface HierarchicalDag<N extends HierarchicalNode<N,E,SO>,
                                 E extends Edge<N,E,SO>,
                                 SO extends SearchOptions>

                                 extends HierarchicalGraph<N,E,SO> {


}
