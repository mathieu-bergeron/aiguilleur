package ca.ntro.core.graphs.generic_graph;

import java.util.List;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.StepsVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.ReachableStepVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.StepsReducer;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<NV extends NodeValue, 
                              EV extends EdgeValue,
                              N extends Node<NV>,
                              E extends Edge<EV>> {

	GraphId id();
	String label();

	void write(GraphWriter writer);
	
	Node<NV> findNode(NodeId id);
	Node<NV> findNode(NodeMatcher<NV> matcher);
	Node<NV> findNode(NV nodeValue);
	Node<NV> findNode(String rawNodeId);

	void forEachStartNode(NodeVisitor<NV> visitor);
	<R extends Object> Result<R> reduceStartNodes(R initialValue, NodeReducer<NV,R> reducer);

	void forEachNode(NodeVisitor<NV> visitor);
	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<NV,R> reducer);

	void forEachEdge(EdgeVisitor<NV,EV> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<NV,EV,R> reducer);

	void forEachReachableNode(Node<NV> fromNode, ReachableNodeVisitor<NV,EV> visitor);
	void forEachReachableNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);

	void forEachReachableStep(Node<NV> fromNode, ReachableStepVisitor<NV,EV> visitor);
	void forEachReachableStep(Node<NV> fromNode, SearchOptions options, ReachableStepVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableSteps(Node<NV> fromNode, R initialValue, ReachableStepReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableSteps(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableStepReducer<NV,EV,R> reducer);

	void visitSteps(Node<NV> fromNode, List<Step> steps, StepsVisitor<NV,EV> visitor);
	<R extends Object> Result<R> reduceSteps(Node<NV> fromNode, List<Step> steps, R initialValue, StepsReducer<NV,EV,R> reducer);
	
}
