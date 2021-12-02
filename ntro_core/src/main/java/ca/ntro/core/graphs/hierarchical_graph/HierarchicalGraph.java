package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraph;

public interface HierarchicalGraph<N extends HierarchicalNode<N,E,SO>,
								   E extends Edge<N,E,SO>,
								   SO extends HierarchicalGraphSearchOptionsBuilder>
       
       extends   GenericGraph<N,E,SO> {


}
