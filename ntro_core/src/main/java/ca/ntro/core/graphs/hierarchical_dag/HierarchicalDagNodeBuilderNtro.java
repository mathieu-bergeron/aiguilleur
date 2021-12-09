package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeBuilderNtro;

public class HierarchicalDagNodeBuilderNtro<N extends HierarchicalDagNode<N,E>,
                                            E extends HierarchicalDagEdge<N,E>>

       extends GenericHierarchicalNodeBuilderNtro<N,E,HierarchicalDagSearchOptions, HierarchicalDagNodeBuilder<N,E>>


	   implements HierarchicalDagNodeBuilder<N,E> {

}
