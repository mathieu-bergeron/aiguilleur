package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNodeNtro;
import ca.ntro.core.graphs.generics.graph.ReachableNodeReducer;
import ca.ntro.core.graphs.generics.graph.ReachableNodeVisitor;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericHierarchicalNodeNtro<N extends GenericHierarchicalNode<N,E,SO>,
 									              E extends GenericEdge<N,E,SO>,
 									              SO extends HierarchicalSearchOptions>

       extends        GenericNodeNtro<N,E,SO> 

	   implements     GenericHierarchicalNode<N,E,SO> {

	public GenericHierarchicalNodeNtro() {
	}

	public GenericHierarchicalNodeNtro(NodeId id) {
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
		
		InternalSearchOptionsNtro subNodeOptions = new InternalSearchOptionsNtro();
		subNodeOptions.copyOptions(options.internal());

		subNodeOptions.setDirections(new Direction[] {Direction.DOWN});

		_reduceReachableNodes(subNodeOptions, result, reducer);
		
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
		
		InternalSearchOptionsNtro parentNodeOptions = new InternalSearchOptionsNtro();
		parentNodeOptions.copyOptions(options.internal());
		parentNodeOptions.setDirections(new Direction[] {Direction.UP});
		
		_reduceReachableNodes(parentNodeOptions, result, reducer);
		
		return result;
	}
}
