package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptions;
import ca.ntro.core.graphs.graph_writer.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalGraphWriterNtro<N extends GenericNode<N,E,SO>,
                                          E extends GenericEdge<N,E,SO>,
                                          SO extends SearchOptions> 

       implements InternalGraphWriter<N,E,SO> {

	@Override
	public void write(GenericGraph<N,E,SO> graph, GraphWriter writer) {

		writer.initialize(graph.id(), defaultOptions());

		writeAfterInitialization(graph, writer);
	}

	protected void writeAfterInitialization(GenericGraph<N, E, SO> graph, GraphWriter writer) {
		writeNodes(graph, writer);
		
		writeEdges(graph, writer);
		
		writeFiles(writer);
	}

	protected void writeFiles(GraphWriter writer) {
		writer.writeDot();
		writer.writePng();
	}

	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(false);
	}
	
	
	protected void adjustNodeSpecAttributes(GenericNode<N,E,SO> node, NodeSpecNtro nodeSpec) {
		if(node.isStartNode()) {
			nodeSpec.setColor("gray");
		}
	}
	
	
	protected NodeSpecNtro nodeSpec(GenericNode<N,E,SO> node) {
		NodeSpecNtro nodeSpec = new NodeSpecNtro(node);
		
		adjustNodeSpecAttributes(node, nodeSpec);
		
		return nodeSpec;
	}

	protected EdgeSpecNtro edgeSpec(GenericEdge<N,E,SO> edge) {
		EdgeSpecNtro edgeSpec = new EdgeSpecNtro(edge);

		return edgeSpec;
	}

	protected void writeNodes(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachNode(n -> {
			try {

				writer.addNode(nodeSpec(n));

			} catch (GraphWriterException e) {
				Ntro.exceptionThrower().throwException(e);
			}
		});
	}

	protected void writeEdges(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachEdge((edge) -> {
			if(edge.type().direction() == Direction.FORWARD) {
				writeEdge(writer, edge);
			}
		});
	}

	protected void writeEdge(GraphWriter writer, E edge) {
		try {

			writer.addEdge(nodeSpec(edge.from()), edgeSpec(edge), nodeSpec(edge.to()));

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
	}
}
