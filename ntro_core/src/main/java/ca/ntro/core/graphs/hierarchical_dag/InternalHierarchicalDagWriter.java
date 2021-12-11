package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.InternalHierarchicalGraphWriter;

public interface InternalHierarchicalDagWriter<N extends HierarchicalDagNode<N,E>,
                                               E extends HierarchicalDagEdge<N,E>>

       extends   InternalHierarchicalGraphWriter<N,E,HierarchicalDagSearchOptions,HierarchicalDagWriterOptions> {

}
