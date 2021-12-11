package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilder;
import ca.ntro.core.graphs.generics.hierarchical_graph.HierarchicalGraphWriterOptions;

public interface HierarchicalGraphBuilder<N extends HierarchicalGraphNode<N,E>,
                                          E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraphBuilder<N,
                                               E,
                                               HierarchicalGraphSearchOptions,
                                               HierarchicalGraphNodeBuilder<N,E>,
                                               HierarchicalGraphWriterOptions,
                                               HierarchicalGraph<N,E>> {

	static <N extends HierarchicalGraphNodeNtro<N,E>, E extends HierarchicalGraphEdgeNtro<N,E>> 

	      HierarchicalGraphBuilder<N,E> newBuilder(HierarchicalNodeFactory<N,E> nodeFactory, HierarchicalEdgeFactory<N,E> edgeFactory) {
		
		HierarchicalGraphBuilderNtro<N,E> builder = new HierarchicalGraphBuilderNtro<N,E>();
		
		builder.setNodeFactory(nodeFactory);
		builder.setEdgeFactory(edgeFactory);
		
		builder.initialize();

		return builder;
	}

	static <N extends HierarchicalGraphNodeNtro<N,E>, E extends HierarchicalGraphEdgeNtro<N,E>> 

	      HierarchicalGraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {

	    
		HierarchicalNodeFactoryNtro<N,E> nodeFactory = new HierarchicalNodeFactoryNtro<N, E>();
		nodeFactory.setNodeClass(nodeClass);
		
		HierarchicalEdgeFactoryNtro<N,E> edgeFactory = new HierarchicalEdgeFactoryNtro<N,E>();
		edgeFactory.setEdgeClass(edgeClass);

		return newBuilder(nodeFactory, edgeFactory);
	}
}
