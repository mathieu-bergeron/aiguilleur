package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalEdgeFactoryNtro;

public class  HierarchicalDagEdgeFactoryNtro<N extends HierarchicalDagNode<N,E>,
                                             E extends HierarchicalDagEdge<N,E>> 

       extends GenericHierarchicalEdgeFactoryNtro<N,E,HierarchicalDagSearchOptions> 

       implements HierarchicalDagEdgeFactory<N,E> {
	
}
