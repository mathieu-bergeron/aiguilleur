package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface DirectedGraphBuilder<NV extends NodeValue, EV extends EdgeValue> extends GenericGraphBuilder<NV,EV, DirectedGraphStructure<NV,EV>, DirectedGraph<NV,EV>> {
	
	public static <NV extends NodeValue, EV extends EdgeValue> DirectedGraphBuilder<NV,EV> newBuilder(){
		return new DirectedGraphNtro<NV,EV>();
	}

	public static <NV extends NodeValue, EV extends EdgeValue> DirectedGraphBuilder<NV,EV> newBuilder(String graphName){
		return new DirectedGraphNtro<NV,EV>(graphName);
	}

}
