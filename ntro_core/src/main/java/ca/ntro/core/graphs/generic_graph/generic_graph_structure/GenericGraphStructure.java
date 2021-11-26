package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.WalkedStepReducer;
import ca.ntro.core.graphs.generic_graph.StepReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure<NV extends NodeValue, EV extends EdgeValue> {

	boolean containsNode(Node<NV> node);
	boolean containsEdge(Edge<EV> edge);

	Node<NV> createNode(NV nodeValue);
	Edge<EV> createEdge(Node<NV> from, EV edgeValue, Node<NV> to);

	void memorizeNode(Node<NV> node);
	void memorizeEdge(Node<NV> from, Edge<EV> edge, Node<NV> to);

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer);

	<R> void reduceNextSteps(Node<NV> fromNode, ResultNtro<R> result, StepReducer<R> reducer);

	<R> void walkStep(Node<NV> fromNode, Step step, ResultNtro<R> result, WalkedStepReducer<NV, EV, R> reducer);

}
