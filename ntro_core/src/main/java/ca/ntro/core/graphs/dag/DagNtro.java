package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.Node;
import ca.ntro.core.graphs.generic_graph.NodeValue;
import ca.ntro.core.wrappers.result.Result;

public class DagNtro<NV extends NodeValue, EV extends EdgeValue> extends DirectedGraphNtro<NV,EV> implements Dag<NV,EV> {

	@Override
	protected void detectCycleFrom(Node<NV> from) {
		Result<Void> result = reduceReachableNodes(from, null, (accumulator, distance, n) -> {
			if(from == n) {
				throw new CycleException();
			}

			return null;
		});
		
		result.throwException();
	}

}
