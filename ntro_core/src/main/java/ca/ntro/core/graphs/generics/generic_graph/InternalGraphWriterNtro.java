package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.graphs.graph_writer.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptions;
import ca.ntro.core.graphs.graph_writer.NodeAlreadyAddedException;
import ca.ntro.core.graphs.graph_writer.NodeSpec;
import ca.ntro.core.graphs.graph_writer.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalGraphWriterNtro<N extends Node<N,E,SO>,
                                          E extends Edge<N,E,SO>,
                                          SO extends SearchOptionsBuilder> 

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
	
	
	protected void adjustNodeSpecAttributes(Node<N,E,SO> node, NodeSpecNtro nodeSpec) {
		if(node.isStartNode()) {
			nodeSpec.setColor("gray");
		}
	}
	
	
	protected NodeSpecNtro nodeSpec(Node<N,E,SO> node) {
		NodeSpecNtro nodeSpec = new NodeSpecNtro(node);
		
		adjustNodeSpecAttributes(node, nodeSpec);
		
		return nodeSpec;
	}

	protected EdgeSpecNtro edgeSpec(Edge<N,E,SO> edge) {
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
