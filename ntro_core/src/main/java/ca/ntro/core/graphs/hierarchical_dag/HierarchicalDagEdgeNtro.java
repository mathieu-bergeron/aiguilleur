package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class      HierarchicalDagEdgeNtro<N extends HierarchicalDagNode<N,E>,
                                          E extends HierarchicalDagEdge<N,E>>

       extends    GenericEdgeNtro<N,E,HierarchicalDagSearchOptions>

       implements HierarchicalDagEdge<N,E> {

}
