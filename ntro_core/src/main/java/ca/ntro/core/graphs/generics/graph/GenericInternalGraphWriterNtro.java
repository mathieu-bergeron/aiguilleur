package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      GenericInternalGraphWriterNtro<N extends GenericNode<N,E,SO>,
                                          E extends GenericEdge<N,E,SO>,
                                          SO extends SearchOptions,
                                          GO extends GraphWriterOptions> 

       implements GenericInternalGraphWriter<N,E,SO,GO> {

	@Override
	public void write(GenericGraph<N,E,SO,GO> graph, GO options, GraphWriter writer) {

		writer.initialize(graph.id(), options);

		writeAfterInitialization(graph, options, writer);
	}

	protected void writeAfterInitialization(GenericGraph<N,E,SO,GO> graph, 
			                                GO options,
			                                GraphWriter writer) {

		writeNodes(graph, options, writer);

		writeEdges(graph, options, writer);
		
		writeFiles(writer);
	}

	protected void writeFiles(GraphWriter writer) {
		writer.writeDot();
		writer.writePng();
	}
	
	protected void adjustNodeSpecAttributes(GenericNode<N,E,SO> node, 
			                                GO options,
			                                NodeSpecNtro nodeSpec) {
		if(node.isStartNode()) {
			nodeSpec.setColor("gray");
		}
	}
	
	
	protected NodeSpecNtro nodeSpec(GenericNode<N,E,SO> node, GO options) {
		NodeSpecNtro nodeSpec = new NodeSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, nodeSpec);
		
		return nodeSpec;
	}

	protected EdgeSpecNtro edgeSpec(GenericEdge<N,E,SO> edge, GO options) {
		EdgeSpecNtro edgeSpec = new EdgeSpecNtro(edge);

		return edgeSpec;
	}

	protected void writeNodes(GenericGraph<N,E,SO,GO> graph, 
			                  GO options,
			                  GraphWriter writer) {
		
		graph.nodes().forEach(n -> {
			try {

				writer.addNode(nodeSpec(n, options));

			} catch (GraphWriterException e) {
				Ntro.exceptionThrower().throwException(e);
			}
		});
	}

	protected void writeEdges(GenericGraph<N,E,SO,GO> graph, 
			                  GO options,
			                  GraphWriter writer) {
		
		graph.edges().forEach(edge -> {
			if(edge.type().direction() == Direction.FORWARD) {
				writeEdge(writer, options, edge);
			}
		});
	}

	protected void writeEdge(GraphWriter writer, 
			                 GO options,
			                 E edge) {
		try {

			writer.addEdge(nodeSpec(edge.from(), options), edgeSpec(edge, options), nodeSpec(edge.to(), options));

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
	}
}
