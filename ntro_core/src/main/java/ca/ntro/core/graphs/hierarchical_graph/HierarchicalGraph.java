package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraph;
import ca.ntro.core.graphs.generics.hierarchical_graph.HierarchicalGraphWriterOptions;

public interface HierarchicalGraph<N extends HierarchicalGraphNode<N,E>,
                                   E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraph<N,E,HierarchicalGraphSearchOptions,HierarchicalGraphWriterOptions> {

}
