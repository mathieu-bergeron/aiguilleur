package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalGraph<NV extends NodeValue, 
                                   EV extends EdgeValue,
                                   N extends Node<NV>,
                                   E extends Edge<EV>> extends GenericGraph<NV,EV,N,E> {

	void forEachSubNode(Node<NV> parentNode, ReachableNodeVisitor<NV,EV> visitor);
	void forEachSubNode(Node<NV> parentNode, SearchOptions options, ReachableNodeVisitor<NV,EV> visitor);
	
	<R extends Object> Result<R> reduceSubNodes(Node<NV> parentNode, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceSubNodes(Node<NV> parentNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);

	void forEachParentNode(Node<NV> parentNode, ReachableNodeVisitor<NV,EV> visitor);
	void forEachParentNode(Node<NV> parentNode, SearchOptions options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceParentNodes(Node<NV> parentNode, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceParentNodes(Node<NV> parentNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);

}
