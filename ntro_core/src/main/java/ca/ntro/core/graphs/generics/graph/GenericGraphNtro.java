package ca.ntro.core.graphs.generics.graph;


import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public abstract class GenericGraphNtro<N extends GenericNode<N,E,SO>, 
                                       E extends GenericEdge<N,E,SO>,
                                       SO extends SearchOptions,
                                       GO extends GraphWriterOptions> 

       implements     GenericGraph<N,E,SO,GO> {
	
	private GraphId id;
	private GenericInternalGraphWriter<N,E,SO,GO> internalGraphWriter = newInternalGraphWriterInstance();
	private GenericGraphStructure<N,E,SO> graphStructure;

	protected abstract GenericInternalGraphWriter<N,E,SO,GO> newInternalGraphWriterInstance();
	protected abstract SO newDefaultSearchOptionsInstance();
	protected abstract GO newDefaultGraphWriterOptionsInstance();

	@Override
	public SO defaultSearchOptions() {
		return newDefaultSearchOptionsInstance();
	}

	@Override
	public GO defaultGraphWriterOptions () {
		return newDefaultGraphWriterOptionsInstance();
	}

	public GenericInternalGraphWriter<N,E,SO,GO> getInternalGraphWriter() {
		return internalGraphWriter;
	}

	public void setInternalGraphWriter(GenericInternalGraphWriter<N,E,SO,GO> internalGraphWriter) {
		this.internalGraphWriter = internalGraphWriter;
	}

	public GenericGraphStructure<N, E, SO> getGraphStructure() {
		return graphStructure;
	}

	public void setGraphStructure(GenericGraphStructure<N,E,SO> graphStructure) {
		this.graphStructure = graphStructure;
	}

	public GenericInternalGraphWriter<N,E,SO,GO> internalGraphWriter(){
		return getInternalGraphWriter();
	}

	public GenericGraphStructure<N,E,SO> graphStructure(){
		return getGraphStructure();
	}

	public GraphId getId() {
		return id;
	}

	public void setId(GraphId id) {
		this.id = id;
	}

	@Override
	public GraphId id() {
		return getId();
	}

	@Override
	public String label() {
		return getId().toKey().toString();
	}

	@Override
	public void write(GraphWriter writer) {
		internalGraphWriter().write(this, defaultGraphWriterOptions(), writer);
	}

	@Override
	public void write(GO options, GraphWriter writer) {
		internalGraphWriter().write(this, options, writer);
	}

	@Override
	public N findNode(String nodeId) {
		return findNode(new NodeIdNtro(nodeId));
	}

	@Override
	public N findNode(NodeId nodeId) {
		return nodes().findFirst(n -> n.id().equals(nodeId));
	}

	public Stream<N> startNodes(){
		return graphStructure().startNodes();
	}

	public Stream<N> nodes(){
		return visitNodes().map(vn -> vn.node());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> visitNodes(){
		return visitNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> visitNodes(SO options){

		return startNodes().reduceToStream((startNode, nodeVisitor) -> {

			if(!options.internal().visitedNodes().contains(startNode.id().toKey().toString())) {
				options.internal().visitedNodes().add(startNode.id().toKey().toString());

				nodeVisitor.visit(new VisitedNodeNtro<N,E,SO>(new WalkNtro<N,E,SO>(), (GenericNodeNtro<N,E,SO>) startNode));

				startNode.reachableNodes(options).forEach(visitedNode -> {

					nodeVisitor.visit(visitedNode);
				});
			}
		});
	}

	protected SO forwardOptions() {
		
		SO options = defaultSearchOptions();

		InternalSearchOptionsNtro forwardOptions = new InternalSearchOptionsNtro();

		forwardOptions.copyOptions(options.internal());
		forwardOptions.setDirections(new Direction[] {Direction.FORWARD});
		
		options.copyOptions(forwardOptions);
		
		return options;
	}

	public Stream<E> edges(){
		return visitEdges().map(ve -> ve.edge());
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> visitEdges(SO options){
		return visitNodes(options).reduceToStream((visitedNode, edgeVisitor) -> {

			visitedNode.node().edges(forwardOptions()).forEach(e -> {

				edgeVisitor.visit(new VisitedEdgeNtro<N,E,SO>((WalkNtro<N,E,SO>) visitedNode.walked(), e));
			});
		});
	}

	@Override
	public Stream<VisitedEdge<N,E,SO>> visitEdges(){
		return visitEdges(defaultSearchOptions());
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

				startNodes().forEach(startNode -> {

					startNode.walk(walk, options).forEach(walked -> {

						visitor.visit(walked);
					});
				});
			}
		};
	}
}