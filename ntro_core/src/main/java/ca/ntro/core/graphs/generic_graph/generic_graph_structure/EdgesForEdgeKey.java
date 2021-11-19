package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class EdgesForEdgeKey<NV extends NodeValue, EV extends EdgeValue> {
	
	private Map<String, Node<NV>> edges = new HashMap<>();

	public void addEdge(Edge<EV> edge, Node<NV> to) {
		edges.put(edge.id().toKey(), to);
	}
}
