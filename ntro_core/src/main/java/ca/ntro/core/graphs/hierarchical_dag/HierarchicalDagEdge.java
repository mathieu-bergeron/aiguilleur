package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface HierarchicalDagEdge<N extends HierarchicalDagNode<N,E>,
                                     E extends HierarchicalDagEdge<N,E>>

       extends   GenericEdge<N,E,HierarchicalDagSearchOptions> {

}
