package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.stream.Reducer;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericNodeNtro<N extends GenericNode<N,E,SO>, 
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> 

      implements      GenericNode<N,E,SO> {
	
	private NodeId nodeId;
	private GenericNodeStructure<N,E,SO> nodeStructure;
	private GenericGraph<N,E,SO,?> graph;

	public NodeId getNodeId() {
		return nodeId;
	}

	public void setNodeId(NodeId nodeId) {
		this.nodeId = nodeId;
	}

	public GenericNodeStructure<N, E, SO> getNodeStructure() {
		return nodeStructure;
	}

	public GenericGraph<N,E,SO,?> getGraph() {
		return graph;
	}

	public void setGraph(GenericGraph<N,E,SO,?> graph) {
		this.graph = graph;
	}

	public void setNodeStructure(GenericNodeStructure<N, E, SO> nodeStructure) {
		this.nodeStructure = nodeStructure;
	}

	protected GenericNodeStructure<N, E, SO> nodeStructure() {
		return getNodeStructure();
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

	public GenericNodeNtro() {
	}

	@Override
	public NodeId id() {
		return getNodeId();
	}

	@Override
	public String label() {
		return id().toKey().toString();
	}

	/*
	@Override
	public void forEachEdge(EdgeVisitor<N,E,SO> visitor) {
		reduceEdges(null, (__, e) -> {
			
			visitor.visitEdge(e);
			
			return null;

		}).throwException();
	}*/

	/*
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
	}*/

	protected SO defaultSearchOptions() {
		return parentGraph().defaultSearchOptions();
	}

	/*
	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor) {
		forEachReachableNode(defaultSearchOptions(), visitor);
	}*/

	/*
	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor) {
		reduceReachableNodes(options, null, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			
			return null;

		}).throwException();
	}*/

	protected void _forEachReachableNode(InternalSearchOptions options, ReachableNodeVisitor<N,E,SO> visitor) {
		ResultNtro<?> result = new ResultNtro<>();

		_reduceReachableNodes(options, result, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			return null;

		});

		result.throwException();
	}

	protected <R> void _reduceReachableNodes(InternalSearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		_forEachReachableEdge(options, (walked, edge) -> {
			if(options.visitedNodes().contains(edge.to().id().toKey().toString())) {
				return;
			}

			try {
				
				result.registerValue(reducer.reduceReachableNode(result.value(), walked, edge.to()));

			} catch(Throwable t) {
				
				result.registerException(t);
				throw new Break();
			}

			options.visitedNodes().add(edge.to().id().toKey().toString());
		});
	}

	protected <R> void __reduceReachableNodes(InternalSearchOptions options, 
			                                  ResultNtro<R> result, 
			                                  Reducer<VisitedNode<N,E,SO>, R> _reducer) {

		if(result.hasException()) {
			return;
		}
		
		_forEachReachableEdge(options, (walked, edge) -> {
			if(options.visitedNodes().contains(edge.to().id().toKey().toString())) {
				return;
			}

			try {
				
				_reducer.reduce(result, new VisitedNodeNtro<N,E,SO>((WalkNtro<N,E,SO>) walked, (GenericNodeNtro<N,E,SO>) edge.to()));

			} catch(Throwable t) {
				
				result.registerException(t);
				throw new Break();
			}

			options.visitedNodes().add(edge.to().id().toKey().toString());
		});
	}

	protected <R> void _forEachReachableEdge(InternalSearchOptions options, ReachableEdgeVisitor<N,E,SO> visitor) {
		ResultNtro<R> result = new ResultNtro<R>(null);

		_reduceReachableEdges(options, result, (accumulator, walked, edge) -> {

			visitor.visitReachableEdge(walked, edge);

			return null;
		});
		
		result.throwException();
	}

	protected <R> void _reduceReachableEdges(InternalSearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableEdgeReducer<N,E,SO,R> reducer) {
		
		Walk<N,E,SO> walked = new WalkNtro<>();
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(options, walked, result, reducer);

		}else {

	        InternalSearchOptionsNtro oneStepOptions = new InternalSearchOptionsNtro();
	        oneStepOptions.copyOptions(options);
			oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
			oneStepOptions.setMaxDistance(1);
 
			_reduceReachableEdgesBreadthFirst(options, oneStepOptions, walked, result, reducer);
		}
	}
	
	protected SO oneStepOptions() {
		InternalSearchOptionsNtro oneStepOptions = new InternalSearchOptionsNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.setMaxDistance(1);
		
		SO options = defaultSearchOptions();
		options.copyOptions(oneStepOptions);
		
		return options;
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceReachableEdgesDepthFirst(InternalSearchOptions options, 
			                                           Walk<N,E,SO> walked,
			                                           ResultNtro<R> result, 
			                                           ReachableEdgeReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		for(Direction direction : options.directions()) {

			nodeStructure().reduceEdgeTypesForDirection(direction, result, (__, edgeType) -> {

				nodeStructure().reduceEdgesByType(edgeType, result, (___, edge) -> {
					if(options.visitedEdges().contains(edge.id().toKey().toString())) {
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
					
					options.visitedEdges().add(edge.id().toKey().toString());

					if(!(options.maxDistance().hasValue() 
							&& newWalked.size() >= options.maxDistance().value())) {
						
						// JSweet: typing error on casting w/o creating a local var
						GenericNodeNtro<N,E,SO> to = (GenericNodeNtro<N,E,SO>) edge.to();

						to._reduceReachableEdgesDepthFirst(options, 
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
	protected <R> void _reduceReachableEdgesBreadthFirst(InternalSearchOptions options, 
			                                             InternalSearchOptions oneStepOptions,
			                                             Walk<N,E,SO> walked,
			                                             ResultNtro<R> result, 
			                                             ReachableEdgeReducer<N,E,SO,R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walked.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(oneStepOptions, new WalkNtro<>(), result, reducer);

		_reduceReachableEdgesDepthFirst(oneStepOptions, new WalkNtro<>(), result, (__, ___, edge) -> {
			
			Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
			newWalked.add(edge);

			// JSweet: typing error on casting w/o creating a local var
			GenericNodeNtro<N,E,SO> to = (GenericNodeNtro<N,E,SO>) edge.to();

			to._reduceReachableEdgesBreadthFirst(options, 
					                             oneStepOptions, 
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
		
		InternalSearchOptionsNtro options = new InternalSearchOptionsNtro();
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

		//return reachableNodes(cycleSearchOptions()).ifSome(visitedNode -> visitedNode.node() == this);
	}
	
	protected SO cycleSearchOptions() {

		InternalSearchOptionsNtro options = new InternalSearchOptionsNtro();
		options.setDirections(new Direction[] {Direction.FORWARD});
		options.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		options.setMaxDistance(Optionnal.none(Integer.class));
		options.setSortEdgesByName(false);

		SO cycleOptions = defaultSearchOptions();
		cycleOptions.copyOptions(options);
		
		return cycleOptions;
	}

	@Override
	public Stream<E> edges(){
		return edges(defaultSearchOptions());
	}
	
	@Override
	public Stream<E> edges(SO options){
		Stream<E> stream = new StreamNtro<E>() {
			@Override
			public void _forEach(Visitor<E> visitor) throws Throwable {

				for(Direction direction : options.internal().directions()){

					// JSweet: explicit Stream variable to avoid typing error
					Stream<EdgeType> edgeTypes = nodeStructure().edgeTypes(direction);

					edgeTypes.forEach(edgeType -> {
						
						Stream<E> edges = nodeStructure().edges(edgeType);
						
						edges.forEach(edge -> { 

							visitor.visit(edge);
						});
					});
				}
			}
		};
		
		return stream;
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(){
		return reachableNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(SO options){
		if(options.internal().searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {
			
			return reachableNodesDepthFirst(options);
			
		} else {
			
			return reachableNodesBreadthFirst(options);
		}
	}

	protected Stream<VisitedNode<N, E, SO>> reachableNodesDepthFirst(SO options) {
		return new StreamNtro<VisitedNode<N,E,SO>>(){
			@Override
			public void _forEach(Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {
				visitReachableNodesDepthFirst(options, new WalkNtro<N,E,SO>(), visitor);
			}
		};
	}

	protected void visitReachableNodesDepthFirst(SO options, 
			                                     WalkNtro<N,E,SO> walked,
			                                     Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {

		if(options.internal().visitedNodes().contains(this.id().toKey().toString())) {
			return;
		}

		if(options.internal().maxDistance().hasValue()
				&& walked.size() >= options.internal().maxDistance().value()) {
			return;
		}
		
		edges(options)._forEach(e -> {
				if(options.internal().visitedNodes().contains(e.to().id().toKey().toString())) {
					return;
				}
			
				WalkNtro<N,E,SO> newWalked = new WalkNtro<N,E,SO>(walked);
				newWalked.add(e);

				options.internal().visitedNodes().add(e.to().id().toKey().toString());

				VisitedNodeNtro<N,E,SO> visitedNode = new VisitedNodeNtro<N,E,SO>(newWalked, (GenericNodeNtro<N,E,SO>) e.to());

				visitor.visit(visitedNode);

				((GenericNodeNtro<N,E,SO>) e.to()).visitReachableNodesDepthFirst(options, newWalked, visitor);
		});
	}

	protected Stream<VisitedNode<N, E, SO>> reachableNodesBreadthFirst(SO options) {
		return new StreamNtro<VisitedNode<N,E,SO>>(){
			@Override
			public void _forEach(Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {
				visitReachableNodesBreadthFirst(options, 
												oneStepOptions(),
						                        new WalkNtro<N,E,SO>(), 
						                        visitor);
			}
		};
	}

	protected void visitReachableNodesBreadthFirst(SO options, 
												   SO oneStepOptions,
			                                       WalkNtro<N,E,SO> walked,
			                                       Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {

		if(options.internal().visitedNodes().contains(this.id().toKey().toString())) {
			return;
		}

		if(options.internal().maxDistance().hasValue()
				&& walked.size() >= options.internal().maxDistance().value()) {
			return;
		}

		visitReachableNodesDepthFirst(oneStepOptions, walked, visitor);

		edges(options)._forEach(e -> {

			if(options.internal().visitedNodes().contains(e.to().id().toKey().toString())) {
				return;
			}

			WalkNtro<N,E,SO> newWalked = new WalkNtro<N,E,SO>(walked);
			newWalked.add(e);

			VisitedNodeNtro<N,E,SO> visitedNode = new VisitedNodeNtro<N,E,SO>(newWalked, (GenericNodeNtro<N,E,SO>) e.to());

			visitor.visit(visitedNode);

			((GenericNodeNtro<N,E,SO>)e.to()).visitReachableNodesBreadthFirst(options, oneStepOptions, newWalked, visitor);
		});
	}
	

	@Override
	public Stream<VisitedEdge<N,E,SO>> reachableEdges(){
		return reachableEdges(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> reachableEdges(SO options){
		return reachableNodes().reduceToStream((visitedNode, edgeVisitor) -> {

			visitedNode.node().edges().forEach(e -> {
				
				VisitedEdgeNtro<N,E,SO> visitedEdge = new VisitedEdgeNtro<N,E,SO>((WalkNtro<N,E,SO>) visitedNode.walked(), e);
				
				edgeVisitor.visit(visitedEdge);
			});
		});
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(){
		return null;
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(SO options){
		return null;
		
	}

	@Override
	public GenericGraph<N,E,SO,?> parentGraph() {
		return getGraph();
	}

	@Override
	public boolean isStartNode() {
		return nodeStructure().isStartNode();
	}
}
