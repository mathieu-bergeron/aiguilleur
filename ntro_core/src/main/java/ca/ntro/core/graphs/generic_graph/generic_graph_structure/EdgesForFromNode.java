package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForFromNode<NV extends NodeValue, EV extends EdgeValue> {
	
	private Map<String, EdgesForEdgeName<NV,EV>> edges = new HashMap<>();

	public void addEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		EdgesForEdgeName<NV,EV> nextEdges = edges.get(from.id().toKey());
		
		if(nextEdges == null) {
			nextEdges = new EdgesForEdgeName<NV,EV>();
			edges.put(from.id().toKey(), nextEdges);
		}

		nextEdges.addEdge(edge, to);
	}

	public <R> void reduceEdgeNames(Node<NV> fromNode, 
			                        ResultNtro<R> result, 
			                        EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForEdgeName<NV,EV> nextEdges = edges.get(fromNode.id().toKey());

		if(nextEdges != null) {
			nextEdges.reduceEdgeNames(result, reducer);
		}
	}

	public <R> void reduceEdgesByName(Node<NV> fromNode, 
			                          String edgeName, 
			                          ResultNtro<R> result, 
			                          ReachableEdgeReducer<NV, EV, R> reducer) {

		EdgesForEdgeName<NV,EV> nextEdges = edges.get(fromNode.id().toKey());
		
		nextEdges.reduceEdgesByName(fromNode, edgeName, result, reducer);

	}

}
