package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalNode<NV extends NodeValue, 
                                  EV extends EdgeValue,
                                  N extends HierarchicalNode<NV,EV,N,E>,
                                  E extends Edge<EV>> 

       extends   Node<NV> {
	
	boolean isCluster();
	boolean isRootNode();

	HierarchicalNode<NV,EV,N,E> parent();

	void forEachSubNode(ReachableNodeVisitor<NV,EV,N,E> visitor);
	void forEachSubNode(SearchOptions options, ReachableNodeVisitor<NV,EV,N,E> visitor);

	<R extends Object> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);
	<R extends Object> Result<R> reduceSubNodes(SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);

	void forEachParentNode(ReachableNodeVisitor<NV,EV,N,E> visitor);
	void forEachParentNode(SearchOptions options, ReachableNodeVisitor<NV,EV,N,E> visitor);

	<R extends Object> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);
	<R extends Object> Result<R> reduceParentNodes(SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,N,E,R> reducer);


}
