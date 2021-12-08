package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilderNtro;

public class      DirectedNodeBuilderNtro<N extends  DirectedNode<N,E>, 
                                          E extends  DirectedEdge<N,E>>

       extends    GenericNodeBuilderNtro<N,E,DirectedSearchOptions,DirectedNodeBuilder<N,E>>

       implements DirectedNodeBuilder<N,E> {

	@Override
	public boolean ifEdgeAlreadyExists(E edge) {
		return node().edges().ifSome(e -> {
			return e.equals(edge);
		});
	}
}
