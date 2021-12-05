package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;

public class MockGraph extends GenericGraphNtro<MockNode, MockEdge, GraphSearchOptionsBuilder>{

	public MockGraph(GraphId id, GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
	}

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InternalGraphWriter<MockNode, MockEdge, GraphSearchOptionsBuilder> internalGraphWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphSearchOptionsBuilder defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

}
