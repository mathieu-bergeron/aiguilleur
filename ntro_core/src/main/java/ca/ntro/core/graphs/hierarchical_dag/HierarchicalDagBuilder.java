package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilder;

public interface HierarchicalDagBuilder<N extends HierarchicalDagNode<N,E>,
                                        E extends HierarchicalDagEdge<N,E>>



       extends   GenericHierarchicalGraphBuilder<N,
                                                 E,
                                                 HierarchicalDagSearchOptions,
                                                 HierarchicalDagNodeBuilder<N,E>,
                                                 HierarchicalDagWriterOptions,
                                                 HierarchicalDag<N,E>> {

	static <N extends HierarchicalDagNodeNtro<N,E>, E extends HierarchicalDagEdge<N,E>> 

	      HierarchicalDagBuilder<N,E> newBuilder(HierarchicalDagNodeFactory<N,E> nodeFactory, HierarchicalDagEdgeFactory<N,E> edgeFactory) {
		
		HierarchicalDagBuilderNtro<N,E> builder = new HierarchicalDagBuilderNtro<N,E>();
		
		builder.setNodeFactory(nodeFactory);
		builder.setEdgeFactory(edgeFactory);
		
		builder.initialize();

		return builder;
	}

	static <N extends HierarchicalDagNodeNtro<N,E>, E extends HierarchicalDagEdge<N,E>> 

	      HierarchicalDagBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {

		// JSweet: class instead of lambda to avoid typing errors
		HierarchicalDagNodeFactoryNtro<N,E> nodeFactory = new HierarchicalDagNodeFactoryNtro<>();
		nodeFactory.setNodeClass(nodeClass);
		
		HierarchicalDagEdgeFactoryNtro<N,E> edgeFactory = new HierarchicalDagEdgeFactoryNtro<>();
		edgeFactory.setEdgeClass(edgeClass);

		return newBuilder(nodeFactory, edgeFactory);
	}

	
	

}
