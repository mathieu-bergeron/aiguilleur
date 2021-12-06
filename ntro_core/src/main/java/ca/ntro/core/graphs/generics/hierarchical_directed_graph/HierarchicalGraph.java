package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;

public interface HierarchicalGraph<N extends HierarchicalNode<N,E,SO>,
								   E extends Edge<N,E,SO>,
								   SO extends HierarchicalGraphSearchOptionsBuilder>
       
       extends   GenericDirectedGraph<N,E,SO> {


}
