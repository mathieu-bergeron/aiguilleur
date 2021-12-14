package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.graph.EdgeFactory;
import ca.ntro.core.graphs.graph.EdgeFactoryNtro;
import ca.ntro.core.graphs.graph.NodeFactory;
import ca.ntro.core.graphs.graph.NodeFactoryNtro;

public interface DirectedGraphBuilder<N extends  DirectedNode<N,E>, 
                                      E extends  DirectedEdge<N,E>>

       extends GenericDirectedGraphBuilder<N,
                                           E,
                                           DirectedGraphSearchOptions,
                                           DirectedNodeBuilder<N,E>,
                                           DirectedGraphWriterOptions,
                                           DirectedGraph<N,E>> {

	static <N extends DirectedNodeNtro<N,E>, E extends DirectedEdgeNtro<N,E>> 

	      DirectedGraphBuilder<N,E> newBuilder(DirectedNodeFactory<N,E> nodeFactory, DirectedEdgeFactory<N,E> edgeFactory) {
		
		DirectedGraphBuilderNtro<N,E> builder = new DirectedGraphBuilderNtro<N,E>();
		
		builder.setNodeFactory(nodeFactory);
		builder.setEdgeFactory(edgeFactory);

		builder.initialize();

		return builder;
	}

	static <N extends DirectedNodeNtro<N,E>, E extends DirectedEdgeNtro<N,E>> 

	      DirectedGraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {

		// JSweet: class instead of lambda to avoid typing errors
		DirectedNodeFactoryNtro<N,E> nodeFactory = new DirectedNodeFactoryNtro<>();
		nodeFactory.setNodeClass(nodeClass);
		
		DirectedEdgeFactoryNtro<N,E> edgeFactory = new DirectedEdgeFactoryNtro<N,E>();
		edgeFactory.setEdgeClass(edgeClass);
		
		return newBuilder(nodeFactory, edgeFactory);
	}

}
