package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.wrappers.result.Result;

public class      DagNtro<NV extends NodeValue, 
                          EV extends EdgeValue,
                          N extends Node<NV>,
                          E extends Edge<EV>> 

       extends    DirectedGraphNtro<NV,EV,N,E> 

       implements Dag<NV,EV,N,E> {

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
