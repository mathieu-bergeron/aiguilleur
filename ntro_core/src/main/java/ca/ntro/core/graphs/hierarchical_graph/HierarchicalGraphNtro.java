package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphNtro;
import ca.ntro.core.graphs.generics.hierarchical_graph.HierarchicalGraphWriterOptions;

public class HierarchicalGraphNtro<N extends HierarchicalGraphNode<N,E>,
                                   E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraphNtro<N,E,HierarchicalGraphSearchOptions,HierarchicalGraphWriterOptions> 

       implements HierarchicalGraph<N,E> { 
}
