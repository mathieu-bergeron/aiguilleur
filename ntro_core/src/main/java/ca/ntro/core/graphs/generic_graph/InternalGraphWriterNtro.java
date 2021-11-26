package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class InternalGraphWriterNtro<NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<NV,EV>> implements InternalGraphWriter<NV,EV> {

	@Override
	public void write(GenericGraph<NV, EV> graph, GraphWriter writer) {

		writer.initialize(graph.id(), defaultOptions());

		writeNodes(graph, writer);
		
		writeEdges(graph, writer);

		
		writer.writeDot();
		writer.writePng();
	}

	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(false);
	}

	protected void writeNodes(GenericGraph<NV,EV> graph, GraphWriter writer) {
		graph.forEachNode(n -> {
			writer.addNode(new NodeSpecNtro(n));
		});
	}

	protected void writeEdges(GenericGraph<NV,EV> graph, GraphWriter writer) {
		graph.forEachEdge((from, edge, to) -> {
			try {

				writer.addEdge(new NodeSpecNtro(from), new EdgeSpecNtro(edge), new NodeSpecNtro(to));

			} catch (NodeNotFoundException e) {
				
				Ntro.exceptionThrower().throwException(e);
			}
		});
	}
}
