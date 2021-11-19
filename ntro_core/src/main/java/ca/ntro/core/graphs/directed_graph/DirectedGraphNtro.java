package ca.ntro.core.graphs.directed_graph;


import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;

public class      DirectedGraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphBuilderNtro<NV,EV,DirectedGraphStructure<NV,EV>, DirectedGraph<NV,EV>> 
       implements DirectedGraph<NV,EV> {

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions();
	}

	@Override
	protected DirectedGraphStructure<NV, EV> createGraphStructure() {
		return new DirectedGraphStructureNtro<NV,EV>();
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}
}
