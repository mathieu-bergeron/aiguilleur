package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalGraphNtro;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalGraph extends GenericHierarchicalGraphNtro<MockHierarchicalNode,
                                                                 MockHierarchicalEdge,
                                                                 HierarchicalGraphSearchOptionsBuilder>{

	public MockHierarchicalGraph(GraphId id,
			GenericGraphStructure<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> graphStructure) {
		super(id, graphStructure);
	}
}
