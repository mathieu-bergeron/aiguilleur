package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeBuilder;

public interface HierarchicalDagNodeBuilder<N extends HierarchicalDagNode<N,E>,
                                            E extends HierarchicalDagEdge<N,E>>

       extends   GenericHierarchicalNodeBuilder<N,
                                                E,
                                                HierarchicalDagSearchOptions, 
                                                HierarchicalDagNodeBuilder<N,E>> { 
}
