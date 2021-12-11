package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraph;

public interface HierarchicalDag<N extends HierarchicalDagNode<N,E>,
                                 E extends HierarchicalDagEdge<N,E>>

       extends   GenericHierarchicalGraph<N,E,HierarchicalDagSearchOptions,HierarchicalDagWriterOptions> {

}
