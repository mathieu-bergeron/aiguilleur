package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.wrappers.optionnal.Optionnal;

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
		// JSWeet: line number was wrong in error message
		// JSWeet: equals does not exists for N
		return reachableNodes(cycleOptions()).ifSome(visitedNode -> ((Object)visitedNode.node()).equals(this));
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
		return options.internal().directionStream().reduceToStream((direction, edgeVisitor) -> {

			// JSweet: explicit Stream variable to avoid typing error
			Stream<EdgeType> edgeTypes = nodeStructure().edgeTypes(direction);

			edgeTypes.forEach(edgeType -> {
				
				Stream<E> edges = nodeStructure().edges(edgeType);
				
				edges.forEach(edge -> { 

					edgeVisitor.visit(edge);
				});
			});
		});
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(){
		return reachableNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> reachableNodes(SO options){
		// JSweet: explicit class to avoid typing errors
		return new ReachableNodesStreamNtro<N,E,SO>(this, options);
	}

	protected void visitReachableNodesDepthFirst(SO options, 
			                                     WalkNtro<N,E,SO> walked,
			                                     Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {

		if(options.internal().maxDistance().hasValue()
				&& (walked.size()+1) > options.internal().maxDistance().value()) {
			return;
		}
		
		edges(options).forEach_(e -> {
				if(options.internal().visitedNodes().contains(e.to().id().toKey().toString())) {
					return;
				}
			
				WalkNtro<N,E,SO> newWalked = new WalkNtro<N,E,SO>(walked);
				newWalked.add(e);

				options.internal().visitedNodes().add(e.to().id().toKey().toString());

				VisitedNodeNtro<N,E,SO> visitedNode = new VisitedNodeNtro<N,E,SO>(newWalked, genericNodeNtro(e.to()));

				visitor.visit(visitedNode);

				genericNodeNtro(e.to()).visitReachableNodesDepthFirst(options, newWalked, visitor);
		});
	}
	
	@SuppressWarnings("unchecked")
	protected GenericNodeNtro<N,E,SO> genericNodeNtro(N node){
		return (GenericNodeNtro<N, E, SO>) node;
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

		edges(options).forEach_(e -> {

			if(options.internal().visitedNodes().contains(e.to().id().toKey().toString())) {
				return;
			}

			WalkNtro<N,E,SO> newWalked = new WalkNtro<N,E,SO>(walked);
			newWalked.add(e);

			VisitedNodeNtro<N,E,SO> visitedNode = new VisitedNodeNtro<N,E,SO>(newWalked, genericNodeNtro(e.to()));

			visitor.visit(visitedNode);

			genericNodeNtro(e.to()).visitReachableNodesBreadthFirst(options, oneStepOptions, newWalked, visitor);
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
		
		// JSWeet: local variables to avoid typing errors
		Stream<VisitedNode<N,E,SO>> reachableNodes = reachableNodes(options);
				
		return reachableNodes.reduceToStream((visitedNode, edgeVisitor) -> {
			
			Stream<E> edges = visitedNode.node().edges(forwardOptions(options));
			
			edges.forEach(e -> {
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
			public void forEach_(Visitor<WalkInProgress<N, E, SO>> visitor) throws Throwable {
				
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

		} else {
			
			while(remainingWalk.size() > 0) {

				remainingWalk = remainingWalk.subWalk(1);
				
				WalkInProgressNtro<N,E,SO> walkInProgress = new WalkInProgressNtro<N,E,SO>(walked, remainingWalk);
				
				visitor.visit(walkInProgress);
			}
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
