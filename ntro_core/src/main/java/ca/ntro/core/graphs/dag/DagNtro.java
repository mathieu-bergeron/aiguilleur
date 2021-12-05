package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public abstract class DagNtro<N extends Node<N,E,SO>,
                              E extends Edge<N,E,SO>,
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
