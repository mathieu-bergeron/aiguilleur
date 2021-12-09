package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeNtro;

public class HierarchicalDagNodeNtro<N extends HierarchicalDagNode<N,E>,
                                     E extends HierarchicalDagEdge<N,E>>

       extends     GenericHierarchicalNodeNtro<N,E,HierarchicalDagSearchOptions>

       implements  HierarchicalDagNode<N,E> {

}
