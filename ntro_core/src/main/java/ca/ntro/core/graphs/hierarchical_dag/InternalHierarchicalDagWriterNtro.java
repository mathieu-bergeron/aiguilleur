package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericInternalHierarchicalGraphWriterNtro;

public class      InternalHierarchicalDagWriterNtro<N extends HierarchicalDagNode<N,E>,
                                                    E extends HierarchicalDagEdge<N,E>>

       extends    GenericInternalHierarchicalGraphWriterNtro<N,E,HierarchicalDagSearchOptions,HierarchicalDagWriterOptions>

       implements InternalHierarchicalDagWriter<N,E> {

}
