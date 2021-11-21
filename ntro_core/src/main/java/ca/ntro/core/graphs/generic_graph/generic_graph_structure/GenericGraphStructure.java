package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure<NV extends NodeValue, EV extends EdgeValue> {

	void memorizeNode(Node<NV> node);
	Edge<EV> memorizeEdge(Node<NV> from, EV edgeValue, Node<NV> to);

	<R> void reduceRootNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer);

	<R> void reduceEdgeNames(Node<NV> fromNode, Direction direction, ResultNtro<R> result, EdgeNameReducer<R> reducer);

	<R> void reduceEdgesByName(Node<NV> fromNode, Direction direction, String edgeName, ResultNtro<R> result, ReachableEdgeReducer<NV, EV, R> reducer);

}