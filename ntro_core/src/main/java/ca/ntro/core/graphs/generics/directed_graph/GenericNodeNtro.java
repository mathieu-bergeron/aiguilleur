package ca.ntro.core.graphs.generics.directed_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream._Reducer;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericNodeNtro<N extends GenericNode<N,E,SO>, 
                               E extends Edge<N,E,SO>,
                               SO extends SearchOptionsBuilder> 

      implements      GenericNode<N,E,SO> {
	
	private NodeId nodeId;

	public NodeId getNodeId() {
		return nodeId;
	}

	public void setNodeId(NodeId nodeId) {
		this.nodeId = nodeId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof GenericNodeNtro) {
			GenericNodeNtro n = (GenericNodeNtro) o;
			
			if(n.nodeId == null && nodeId != null) {
				return false;
			}

			if(n.nodeId != null && !n.nodeId.equals(nodeId)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public GenericNodeNtro(NodeId nodeId) {
		setNodeId(nodeId);
	}

	public GenericNodeNtro(String nodeId) {
		setNodeId(new NodeIdNtro(nodeId));
	}

	@Override
	public NodeId id() {
		return getNodeId();
	}
	
	protected abstract GenericNodeStructure<N,E,SO> nodeStructure();

	@Override
	public void forEachEdge(EdgeVisitor<N,E,SO> visitor) {
		reduceEdges(null, (__, e) -> {
			
			visitor.visitEdge(e);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Direction direction : Direction.values()) {

			nodeStructure().reduceEdgeTypesForDirection(direction, result, (__, edgeType) -> {

				nodeStructure().reduceEdgesByType(edgeType, result, reducer);
				
				return result.value();
			});
		}
		
		return result;
	}

	protected SO defaultSearchOptions() {
		return parentGraph().defaultSearchOptions();
	}

	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor) {
		forEachReachableNode(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor) {
		reduceReachableNodes(options, null, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			
			return null;

		}).throwException();
	}

	protected void _forEachReachableNode(SearchOptions options, ReachableNodeVisitor<N,E,SO> visitor) {
		ResultNtro<?> result = new ResultNtro<>();

		_reduceReachableNodes(options, result, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			return null;

		});

		result.throwException();
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {
		return reduceReachableNodes(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceReachableNodes(options.internal(), result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableNodes(SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedNodes = new HashSet<>();
		
		_forEachReachableEdge(options, (walked, edge) -> {
			if(visitedNodes.contains(edge.to().id().toKey().toString())) {
				return;
			}

			try {
				
				result.registerValue(reducer.reduceReachableNode(result.value(), walked, edge.to()));

			} catch(Throwable t) {
				
				result.registerException(t);
				throw new Break();
			}

			visitedNodes.add(edge.to().id().toKey().toString());
		});
	}

	protected <R> void __reduceReachableNodes(SearchOptions options, 
			                                  ResultNtro<R> result, 
			                                  _Reducer<VisitedNode<N,E,SO>, R> _reducer) {

		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedNodes = new HashSet<>();
		
		_forEachReachableEdge(options, (walked, edge) -> {
			if(visitedNodes.contains(edge.to().id().toKey().toString())) {
				return;
			}

			try {
				
				_reducer._reduce(result, new VisitedNodeNtro<N,E,SO>(walked, edge.to()));

			} catch(Throwable t) {
				
				result.registerException(t);
				throw new Break();
			}

			visitedNodes.add(edge.to().id().toKey().toString());
		});
	}

	@Override
	public void forEachReachableEdge(ReachableEdgeVisitor<N,E,SO> visitor) {
		forEachReachableEdge(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableEdge(SO options, ReachableEdgeVisitor<N,E,SO> visitor) {
		_forEachReachableEdge(options.internal(), visitor);
	}

	protected <R> void _forEachReachableEdge(SearchOptions options, ReachableEdgeVisitor<N,E,SO> visitor) {
		ResultNtro<R> result = new ResultNtro<R>(null);

		_reduceReachableEdges(options, result, (accumulator, walked, edge) -> {

			visitor.visitReachableEdge(walked, edge);

			return null;
		});
		
		result.throwException();
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer) {
		return reduceReachableEdges(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableEdges(options.internal(), result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableEdges(SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableEdgeReducer<N,E,SO,R> reducer) {
		
		Set<String> visitedEdges = new HashSet<>();
		Walk<N,E,SO> walked = new WalkNtro<>();
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(options, visitedEdges, walked, result, reducer);

		}else {

	        SearchOptionsNtro oneStepOptions = new SearchOptionsNtro();
	        oneStepOptions.copyOptions(options);
			oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
			oneStepOptions.setMaxDistance(1);
 
			_reduceReachableEdgesBreadthFirst(options, oneStepOptions, visitedEdges, walked, result, reducer);
		}
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceReachableEdgesDepthFirst(SearchOptions options, 
			                                           Set<String> visitedEdges,
			                                           Walk<N,E,SO> walked,
			                                           ResultNtro<R> result, 
			                                           ReachableEdgeReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		for(Direction direction : options.directions()) {

			nodeStructure().reduceEdgeTypesForDirection(direction, result, (__, edgeType) -> {

				nodeStructure().reduceEdgesByType(edgeType, result, (___, edge) -> {
					if(visitedEdges.contains(edge.id().toKey().toString())) {
						return result.value();
					}
					
					Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
					newWalked.add(edge);
					
					try {
					
						result.registerValue(reducer.reduceEdge(result.value(), newWalked, edge));

					}catch(Throwable t) {
						
						result.registerException(t);
						return result.value();
					}
					
					visitedEdges.add(edge.id().toKey().toString());

					if(!(options.maxDistance().hasValue() 
							&& newWalked.size() >= options.maxDistance().value())) {
						
						// JSweet: typing error on casting w/o creating a local var
						GenericNodeNtro<N,E,SO> to = (GenericNodeNtro<N,E,SO>) edge.to();

						to._reduceReachableEdgesDepthFirst(options, 
													       visitedEdges, 
														   newWalked,
														   result, 
														   reducer);
					}

					return result.value();
				});
				
				return result.value();
			});
		}
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceReachableEdgesBreadthFirst(SearchOptions options, 
			                                             SearchOptions oneStepOptions,
			                                             Set<String> visitedEdges,
			                                             Walk<N,E,SO> walked,
			                                             ResultNtro<R> result, 
			                                             ReachableEdgeReducer<N,E,SO,R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walked.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(oneStepOptions, visitedEdges, new WalkNtro<>(), result, reducer);

		_reduceReachableEdgesDepthFirst(oneStepOptions, visitedEdges, new WalkNtro<>(), result, (__, ___, edge) -> {
			
			Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
			newWalked.add(edge);

			// JSweet: typing error on casting w/o creating a local var
			GenericNodeNtro<N,E,SO> to = (GenericNodeNtro<N,E,SO>) edge.to();

			to._reduceReachableEdgesBreadthFirst(options, 
					                             oneStepOptions, 
					                             visitedEdges, 
					                             newWalked, 
					                             result, 
					                             reducer);
			return result.value();
		});
	}

	@Override
	public void visitWalk(WalkId walk, WalkVisitor<N, E, SO> visitor) {
		reduceWalk(walk, null, (accumulator, walked, remainingWalk, n) -> {
			
			visitor.visitStep(walked, remainingWalk, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceWalk(new WalkNtro<>(), walk, result, reducer);

		return result;
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceWalk(Walk<N,E,SO> walked,
			                       WalkId walk,
			                       ResultNtro<R> result, 
			                       WalkReducer<N,E,SO,R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		if(walk.isEmpty()) {
			return;
		}
		
		EdgeType edgeType = walk.get(0);
		WalkId remainingWalk = (WalkId) walk.subWalk(1);
		
		nodeStructure().reduceEdgesByType(edgeType, result, (__, edge) -> {

			try {
				
				Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
				newWalked.add(edge);

				result.registerValue(reducer.reduceStep(result.value(), newWalked, remainingWalk, edge.to()));

				// JSweet: typing error on casting w/o creating a local var
				GenericNodeNtro<N,E,SO> to = (GenericNodeNtro<N,E,SO>) edge.to();

				to._reduceWalk(newWalked, remainingWalk, result, reducer);

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
			
			return result.value();
		});
	}

	@Override
	public boolean isPartOfCycle() {
		ResultNtro<Boolean> result = new ResultNtro<>(false);
		
		SearchOptionsNtro options = new SearchOptionsNtro();
		options.setDirections(new Direction[] {Direction.FORWARD});
		options.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		options.setMaxDistance(Optionnal.none(Integer.class));
		options.setSortEdgesByName(false);
		
		_forEachReachableNode(options, (walked, reachableNode) -> {

			if(reachableNode == this) {
				result.registerValue(true);
				throw new Break();
			}
		});
		
		return result.value();
	}
	
	@Override
	public Stream<E> edges(){
		return new StreamNtro<E>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<E, R> _reducer) {
				reduceEdges(result.value(), (__, edge) ->{

					_reducer._reduce(result, edge);
					
					return result.value();
				});
			}
		};
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(){
		return reachableNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(SO options){
		return new StreamNtro<VisitedNode<N,E,SO>>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<VisitedNode<N, E, SO>, R> _reducer) {
				__reduceReachableNodes(options.internal(), result, _reducer);
			}
		};
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> reachableEdges(){
		return null;
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> reachableEdges(SO options){
		return null;
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(){
		return null;
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(SO options){
		return null;
		
	}
}
