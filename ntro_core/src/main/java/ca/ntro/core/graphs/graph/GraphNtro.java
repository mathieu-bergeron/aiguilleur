package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;

public class   GraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends GenericGraphNtro<GraphSearchOptions,NV,EV,Graph<NV,EV>> 
       implements Graph<NV,EV>, GraphBuilder<NV,EV> {

	@Override
	protected GraphSearchOptions defaultSearchOptions() {
		return new GraphSearchOptions();
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}

}
