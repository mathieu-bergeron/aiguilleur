package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalGraphWriterNtro<N extends Node<N,E,SO>,
                                          E extends Edge<N,E,SO>,
                                          SO extends SearchOptionsNtro> 

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

	protected void writeNodes(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachNode(n -> {
			writer.addNode(new NodeSpecNtro(n));
		});
	}

	protected void writeEdges(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachEdge((edge) -> {

			writeEdge(writer, edge);

		});
	}

	protected void writeEdge(GraphWriter writer, E edge) {
		try {

			writer.addEdge(new NodeSpecNtro(edge.from()), new EdgeSpecNtro(edge), new NodeSpecNtro(edge.to()));

		} catch (NodeNotFoundException e) {
				
			Ntro.exceptionThrower().throwException(e);
		}
	}
}
