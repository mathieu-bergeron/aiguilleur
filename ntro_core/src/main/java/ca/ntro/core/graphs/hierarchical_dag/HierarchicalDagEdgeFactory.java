package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalEdgeFactory;

public interface HierarchicalDagEdgeFactory<N extends HierarchicalDagNode<N,E>,
                                            E extends HierarchicalDagEdge<N,E>> 

       extends   GenericHierarchicalEdgeFactory<N,E,HierarchicalDagSearchOptions> {
	
}
