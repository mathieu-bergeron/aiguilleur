package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilder;

public interface HierarchicalDagBuilder<N extends HierarchicalDagNode<N,E>,
                                        E extends HierarchicalDagEdge<N,E>>



       extends   GenericHierarchicalGraphBuilder<N,
                                                 E,
                                                 HierarchicalDagSearchOptions,
                                                 HierarchicalDagNodeBuilder<N,E>,
                                                 HierarchicalDag<N,E>> {

	static <N extends HierarchicalDagNodeNtro<N,E>, E extends HierarchicalDagEdge<N,E>> 

	      HierarchicalDagBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		HierarchicalDagBuilderNtro<N,E> builder = new HierarchicalDagBuilderNtro<N,E>();
		
		builder.setNodeClass(nodeClass);
		builder.setEdgeClass(edgeClass);
		
		builder.initialize();

		return builder;
	}

	
	

}
