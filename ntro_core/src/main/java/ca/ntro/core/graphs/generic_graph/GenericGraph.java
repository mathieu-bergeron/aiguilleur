package ca.ntro.core.graphs.generic_graph;


import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<N extends Node<N,E,SO>, 
                              E extends Edge<N,E,SO>,
                              SO extends SearchOptions> {

	GraphId id();
	String label();

	void write(GraphWriter writer);

	N findNode(NodeId id);
	
	void forEachStartNode(NodeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceStartNodes(R initialValue, NodeReducer<N,E,SO,R> reducer);

	void forEachNode(NodeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<N,E,SO,R> reducer);

	void forEachEdge(EdgeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<N,E,SO,R> reducer);

}
