package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface GraphBuilder<NV extends NodeValue, EV extends EdgeValue> extends GenericGraphBuilder<GraphSearchOptions, NV,EV, Graph<NV,EV>> {
	
	public static <NV extends NodeValue, EV extends EdgeValue> GraphBuilder<NV,EV> newBuilder(){
		return new GraphNtro<NV,EV>();
	}

	public static <NV extends NodeValue, EV extends EdgeValue> GraphBuilder<NV,EV> newBuilder(String graphName){
		return new GraphNtro<NV,EV>(graphName);
	}

}
