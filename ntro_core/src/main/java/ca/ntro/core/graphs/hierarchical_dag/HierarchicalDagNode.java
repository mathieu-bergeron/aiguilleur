package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;

public interface HierarchicalDagNode<N extends HierarchicalDagNode<N,E>,
                                     E extends HierarchicalDagEdge<N,E>>

       extends   GenericHierarchicalNode<N,E,HierarchicalDagSearchOptions> {

}
