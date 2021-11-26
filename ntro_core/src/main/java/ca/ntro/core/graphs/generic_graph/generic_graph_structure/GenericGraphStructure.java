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

public interface GenericGraphStructure<NV extends NodeValue, 
                                       EV extends EdgeValue,
                                       N extends Node<NV>,
                                       E extends Edge<EV>> {

	boolean containsNode(N node);
	boolean containsEdge(E edge);

	N createNode(NV nodeValue);
	E createEdge(N from, EV edgeValue, N to);

	void memorizeNode(N node);
	void memorizeEdge(N from, E edge, N to);

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<NV,N,R> reducer);

	<R> void reduceNextSteps(N fromNode, ResultNtro<R> result, StepReducer<R> reducer);

	<R> void walkStep(N fromNode, Step step, ResultNtro<R> result, WalkedStepReducer<NV,EV,N,E,R> reducer);

}
