package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.generic_graph.StepReducer;
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

	public <R> void reduceNextSteps(Node<NV> fromNode, 
									Direction direction,
			                        ResultNtro<R> result, 
			                        StepReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForEdgeName<NV,EV> nextEdges = edges.get(fromNode.id().toKey());

		if(nextEdges != null) {
			nextEdges.reduceSteps(direction, result, reducer);
		}
	}

	public <R> void walkStep(Node<NV> fromNode, 
						     Step step,
			                 ResultNtro<R> result, 
			                 ReachableEdgeReducer<NV, EV, R> reducer) {

		EdgesForEdgeName<NV,EV> nextEdges = edges.get(fromNode.id().toKey());
		
		nextEdges.walkStep(fromNode, step, result, reducer);

	}

}
