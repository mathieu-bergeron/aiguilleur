package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class InternalGraphWriterNtro<NV extends NodeValue, 
                                     EV extends EdgeValue, 
                                     N extends Node<NV>,
                                     E extends Edge<EV>,
                                     G extends GenericGraph<NV,EV,N,E>> implements InternalGraphWriter<NV,EV,N,E> {

	@Override
	public void write(GenericGraph<NV,EV,N,E> graph, GraphWriter writer) {

		writer.initialize(graph.id(), defaultOptions());

		writeNodes(graph, writer);
		
		writeEdges(graph, writer);

		
		writer.writeDot();
		writer.writePng();
	}

	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(false);
	}

	protected void writeNodes(GenericGraph<NV,EV,N,E> graph, GraphWriter writer) {
		graph.forEachNode(n -> {
			writer.addNode(new NodeSpecNtro(n));
		});
	}

	protected void writeEdges(GenericGraph<NV,EV,N,E> graph, GraphWriter writer) {
		graph.forEachEdge((from, edge, to) -> {

			writeEdge(writer, from,edge,to);

		});
	}

	protected void writeEdge(GraphWriter writer, Node<NV> from, Edge<EV> edge, Node<NV> to) {
		try {

			writer.addEdge(new NodeSpecNtro(from), new EdgeSpecNtro(edge), new NodeSpecNtro(to));

		} catch (NodeNotFoundException e) {
				
			Ntro.exceptionThrower().throwException(e);
		}
	}
}
