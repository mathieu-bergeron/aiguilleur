package ca.ntro.core.graphs.generics.graph;


import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;

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
		SO options = defaultSearchOptions();

		return startNodes().reduceToStream((startNode, nodeVisitor) -> {

			if(!options.internal().visitedNodes().contains(startNode.id().toKey().toString())) {
				options.internal().visitedNodes().add(startNode.id().toKey().toString());

				nodeVisitor.visit(startNode);

				startNode.reachableNodes(options).forEach(visitedNode -> {

					nodeVisitor.visit(visitedNode.node());
				});
			}
		});
	}

	public Stream<E> edges(){
		return nodes().reduceToStream((node, edgeVisitor) -> {

			Stream<E> edges = node.edges();

			edges.forEach(e -> edgeVisitor.visit(e));
		});
	}
}