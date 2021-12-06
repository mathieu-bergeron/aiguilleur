package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockGraph extends GraphNtro<MockNode, MockEdge> {

	public MockGraph(GraphId id, GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
		super(id, graphStructure);
	}

}
