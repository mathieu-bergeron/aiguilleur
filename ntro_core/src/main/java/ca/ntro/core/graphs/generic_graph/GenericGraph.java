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
import ca.ntro.core.graphs.StepId;
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
	
	N findNode(NodeId id);
	N findNode(NodeMatcher<NV,N> matcher);
	N findNode(NV nodeValue);
	N findNode(String rawNodeId);

	void forEachStartNode(NodeVisitor<NV,N> visitor);
	<R extends Object> Result<R> reduceStartNodes(R initialValue, NodeReducer<NV,N,R> reducer);

	void forEachNode(NodeVisitor<NV,N> visitor);
	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<NV,N,R> reducer);

	void forEachEdge(EdgeVisitor<NV,EV,N,E> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<NV,EV,N,E,R> reducer);

	void forEachReachableNode(N fromNode, ReachableNodeVisitor<NV,EV,N,E> visitor);
	void forEachReachableNode(N fromNode, SearchOptions options, ReachableNodeVisitor<NV,EV,N,E> visitor);

	<R extends Object> Result<R> reduceReachableNodes(N fromNode, R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N fromNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);

	void forEachReachableStep(N fromNode, ReachableStepVisitor<NV,EV,N,E> visitor);
	void forEachReachableStep(N fromNode, SearchOptions options, ReachableStepVisitor<NV,EV,N,E> visitor);

	<R extends Object> Result<R> reduceReachableSteps(N fromNode, R initialValue, ReachableStepReducer<NV,EV,N,E,R> reducer);
	<R extends Object> Result<R> reduceReachableSteps(N fromNode, SearchOptions options, R initialValue, ReachableStepReducer<NV,EV,N,E,R> reducer);

	void visitSteps(N fromNode, List<StepId> steps, StepsVisitor<NV,EV,N,E> visitor);
	<R extends Object> Result<R> reduceSteps(N fromNode, List<StepId> steps, R initialValue, StepsReducer<NV,EV,N,E,R> reducer);
	
}
