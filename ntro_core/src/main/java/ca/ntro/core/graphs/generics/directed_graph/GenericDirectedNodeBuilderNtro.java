package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilderNtro;

public abstract class GenericDirectedNodeBuilderNtro<N extends GenericDirectedNode<N,E,SO>,
                                                     E extends GenericDirectedEdge<N,E,SO>,
                                                     SO extends DirectedGraphSearchOptions,
									                 NB extends GenericDirectedNodeBuilder<N,E,SO,NB>>

       extends GenericNodeBuilderNtro<N,E,SO,NB> 

       implements GenericDirectedNodeBuilder<N,E,SO,NB> {

	@Override
	public boolean ifEdgeAlreadyExists(E edge) {
		return node().edges().ifSome(e -> {
			return e.equals(edge);
		});
	}

}
