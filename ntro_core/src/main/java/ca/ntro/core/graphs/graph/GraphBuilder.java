package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.NodeValue;

public interface GraphBuilder<NV extends NodeValue, EV extends EdgeValue> extends GenericGraphBuilder<GraphSearchOptions, NV,EV, Graph<NV,EV>> {
	
	public static <NV extends NodeValue, EV extends EdgeValue> GraphBuilder<NV,EV> newBuilder(){
		return new GraphNtro<NV,EV>();
	}

}
