package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.Node;
import ca.ntro.core.wrappers.result.Result;

public class DagNtro<N extends Node, E extends Edge> extends DirectedGraphNtro<N,E> implements Dag<N,E> {

	@Override
	protected void detectCycleFrom(N from) {
		Result<Void> result = reduceReachableNodes(from, null, (accumulator, distance, n) -> {
			if(from == n) {
				throw new CycleException();
			}

			return null;
		});
		
		result.throwException();
	}

}
