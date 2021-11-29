package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class      HierarchicalNodeNtro<N extends HierarchicalNode<N,E,SO>,
 									            E extends Edge<N,E,SO>,
 									            SO extends HierarchicalGraphSearchOptions>

       extends    NodeNtro<N,E,SO> 

	   implements HierarchicalNode<N,E,SO> {


	public HierarchicalNodeNtro(NodeId id) {
		super(id);
	}

	@Override
	public boolean hasSubNodes() {
		return reduceSubNodes(false, (accumulator, walked, n) -> {
			if(accumulator) {
				throw new Break();
			}

			return true;

		}).value();
	}

	@Override
	public boolean hasParent() {
		return reduceParentNodes(false, (accumulator, walked, n) -> {
			if(accumulator) {
				throw new Break();
			}

			return true;

		}).value();
	}

	@Override
	public N parent() {
		Result<N> result = reduceParentNodes(null, (accumulator, walked, n) -> {
			if(accumulator != null) {
				throw new Break();
			}
			
			if(walked.size() == 1) {
				accumulator = n;
			}

			return accumulator;

		});
		
		return result.value();
	}

	@Override
	public void forEachSubNode(ReachableNodeVisitor<N, E, SO> visitor) {
		forEachSubNode(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachSubNode(SO options, ReachableNodeVisitor<N, E, SO> visitor) {
		reduceSubNodes(options, null, (__, walked, n) -> {

			visitor.visitReachableNode(walked, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		return reduceSubNodes(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceSubNodes(SO options, 
			                            R initialValue, 
			                            ReachableNodeReducer<N, E, SO, R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		HierarchicalGraphSearchOptions subNodeOptions = new HierarchicalGraphSearchOptions(options.searchStrategy(), new Direction[] {Direction.DOWN}, options.maxDistance());
		
		_reduceReachableNodes((SO) subNodeOptions, result, reducer);
		
		return result;
	}

	@Override
	public void forEachParentNode(ReachableNodeVisitor<N, E, SO> visitor) {
		forEachParentNode(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachParentNode(SO options, ReachableNodeVisitor<N, E, SO> visitor) {
		reduceParentNodes(options, null, (__, walked, n) -> {

			visitor.visitReachableNode(walked, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<N, E, SO, R> reducer) {
		return reduceParentNodes(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceParentNodes(SO options, 
			                               R initialValue, 
			                               ReachableNodeReducer<N, E, SO, R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		HierarchicalGraphSearchOptions parentNodeOptions = new HierarchicalGraphSearchOptions(options.searchStrategy(), new Direction[] {Direction.UP}, options.maxDistance());
		
		_reduceReachableNodes((SO) parentNodeOptions, result, reducer);
		
		return result;
	}
}
