package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeFactory;

public interface HierarchicalDagNodeFactory<N extends HierarchicalDagNode<N,E>,
                                            E extends HierarchicalDagEdge<N,E>> 


       extends   GenericHierarchicalNodeFactory<N,E,HierarchicalDagSearchOptions> {


}
