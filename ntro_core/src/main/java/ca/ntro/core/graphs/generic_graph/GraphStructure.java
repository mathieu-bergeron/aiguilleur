package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GraphStructure<NV extends NodeValue, EV extends EdgeValue> {
	
	void addEdge(Direction direction, Node<NV> from, Edge<EV> edge, Node<NV> to);

	<R> void reduceEdgeNames(Node<NV> fromNode, Direction direction, ResultNtro<R> result, EdgeNameReducer<R> reducer);

	<R> void reduceEdgesByName(Node<NV> fromNode, Direction direction, String edgeName, ResultNtro<R> result, ReachableEdgeReducer<NV, EV, R> reducer);

}
