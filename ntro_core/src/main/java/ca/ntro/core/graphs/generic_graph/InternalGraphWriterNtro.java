package ca.ntro.core.graphs.generic_graph;

import java.util.HashSet;
import java.util.Set;


import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;

public class InternalGraphWriterNtro<NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<NV,EV>> implements InternalGraphWriter<NV,EV> {

	@Override
	public void write(GenericGraph<NV, EV> graph, GraphWriter writer) {

		writer.initialize(graph.id(), defaultOptions());
		
		Set<String> unwrittenNodes = writeEdges(graph, writer);

		writeNodes(graph, writer, unwrittenNodes);
		
		writer.writeDot();
		writer.writePng();
	}

	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(false);
	}

	protected Set<String> writeEdges(GenericGraph<NV,EV> graph, GraphWriter writer) {

		Set<String> unwrittenNodes = graph.reduceNodes(new HashSet<String>(), (accumulator, n) -> {
			
			accumulator.add(n.id().toKey());

			return accumulator;

		}).value();
		
		graph.forEachEdge((from, edge, to) -> {

			unwrittenNodes.remove(from.id().toKey());
			unwrittenNodes.remove(to.id().toKey());

			writer.addEdge(from, edge, to);
		});
		
		return unwrittenNodes;
	}

	protected void writeNodes(GenericGraph<NV,EV> graph, GraphWriter writer, Set<String> nodesToWrite) {
		for(String nodeKey : nodesToWrite) {

			Node<NV> node = graph.findNode(nodeKey);

			if(node != null) {
				writer.addRootNode(node);
			}
		}
	}
}
