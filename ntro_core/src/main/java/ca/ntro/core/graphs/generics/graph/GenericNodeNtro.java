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
			GenericNode n = (GenericNode) o;
			
			if(n.id() == null && nodeId != null) {
				return false;
			}

			if(n.id() != null && !n.id().equals(nodeId)) {
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
	
	protected SO oneStepOptions() {
		InternalSearchOptionsNtro oneStepOptions = new InternalSearchOptionsNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.setMaxDistance(1);
		
		SO options = defaultSearchOptions();
		options.copyOptions(oneStepOptions);
		
		return options;
	}

	@Override
	public boolean isPartOfCycle() {
		return reachableNodes(cycleOptions()).ifSome(visitedNode -> visitedNode.node().equals(this));
	}

	protected SO cycleOptions() {

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

		if(options.internal().maxDistance().hasValue()
				&& (walked.size()+1) > options.internal().maxDistance().value()) {
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


	protected SO forwardOptions(SO options) {

		InternalSearchOptionsNtro forwardOptions = new InternalSearchOptionsNtro();

		forwardOptions.copyOptions(options.internal());
		forwardOptions.setDirections(new Direction[] {Direction.FORWARD});
		
		options.copyOptions(forwardOptions);
		
		return options;
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> reachableEdges(SO options){
		
		return reachableNodes().reduceToStream((visitedNode, edgeVisitor) -> {

			visitedNode.node().edges(forwardOptions(options)).forEach(e -> {
				VisitedEdgeNtro<N,E,SO> visitedEdge = new VisitedEdgeNtro<N,E,SO>((WalkNtro<N,E,SO>) visitedNode.walked(), e);
				
				edgeVisitor.visit(visitedEdge);
			});
		});
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(WalkId walk){
		return walk(walk, defaultSearchOptions());
	}

	@Override
	public Stream<WalkInProgress<N,E,SO>> walk(WalkId walk, SO options){
		return new StreamNtro<WalkInProgress<N,E,SO>>(){

			@Override
			public void _forEach(Visitor<WalkInProgress<N, E, SO>> visitor) throws Throwable {
				
				visitWalk(new WalkNtro<N,E,SO>(), walk, options, visitor);
				
			}
		};
	}

	protected void visitWalk(WalkNtro<N, E, SO> walked, 
			                 WalkId remainingWalk, 
			                 SO options,
			                 Visitor<WalkInProgress<N, E, SO>> visitor) throws Throwable {
		
		if(remainingWalk.size() <= 0) {
			return;
		}

		if(options.internal().maxDistance().hasValue()
				&& (walked.size()+1) >= options.internal().maxDistance().value()) {
			return;
		}
		
		EdgeType nextEdgeType = remainingWalk.get(0);

		E nextEdge = edges(forwardOptions(options)).findFirst(e -> e.type().equals(nextEdgeType));
		
		if(nextEdge != null) {

			WalkNtro<N,E,SO> newWalked = new WalkNtro<>(walked);
			newWalked.add(nextEdge);
			
			remainingWalk = remainingWalk.subWalk(1);
			
			WalkInProgressNtro<N,E,SO> walkInProgress = new WalkInProgressNtro<N,E,SO>(newWalked, remainingWalk, nextEdge.to());
			
			visitor.visit(walkInProgress);
		}
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
