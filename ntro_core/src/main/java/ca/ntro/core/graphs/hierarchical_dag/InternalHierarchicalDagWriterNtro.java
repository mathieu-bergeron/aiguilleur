package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.InternalHierarchicalGraphWriterNtro;

public class      InternalHierarchicalDagWriterNtro<N extends HierarchicalDagNode<N,E>,
                                                    E extends HierarchicalDagEdge<N,E>>

       extends    InternalHierarchicalGraphWriterNtro<N,E,HierarchicalDagSearchOptions,HierarchicalDagWriterOptions>

       implements InternalHierarchicalDagWriter<N,E> {

}
