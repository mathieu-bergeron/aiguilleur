package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public abstract class DagNtro<N extends GenericNode<N,E,SO>,
                              E extends GenericEdge<N,E,SO>,
                              SO extends DagSearchOptionsBuilder,
                              NB extends GenericNodeBuilder<N,E,SO,NB>>
                         
       extends    DirectedGraphNtro<N,E,SO,NB> 

       implements Dag<N,E,SO> {

	/*
	@Override
	protected void detectCycleFrom(Node<NV> from) {
		Result<Void> result = reduceReachableNodes(from, null, (accumulator, distance, n) -> {
			if(from == n) {
				throw new CycleException();
			}

			return null;
		});
		
		result.throwException();
	}*/

}
