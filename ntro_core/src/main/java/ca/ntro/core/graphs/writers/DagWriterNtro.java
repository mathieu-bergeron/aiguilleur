package ca.ntro.core.graphs.writers;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.Node;
import ca.ntro.core.identifyers.StorageId;

public abstract class DagWriterNtro<N extends Node, E extends Edge> implements DagWriter<N,E> {

	protected abstract ExternalGraphWriter<N, E> createExeternalGraphWriter(StorageId storageId);
	
	public void write(Dag<N, E> dag) {
		
		ExternalGraphWriter<N,E> writer = createExeternalGraphWriter(dag.id());

		Set<String> unwrittenNodes = writeEdges(writer, dag);

		writeNodes(writer, unwrittenNodes, dag);
		
		writer.writePng();
	}

	private Set<String> writeEdges(ExternalGraphWriter<N,E> writer, Dag<N,E> dag) {
		
		Set<String> unwrittenNodes = dag.reduceNodes(new HashSet<String>(), (accumulator, n) -> {
			
			accumulator.add(n.id().toKey());

			return accumulator;

		}).value();
		
		dag.forEachEdge((from, edge, to) -> {

			unwrittenNodes.remove(from.id().toKey());
			unwrittenNodes.remove(to.id().toKey());

			writer.writeEdge(from, edge, to);
		});
		
		return unwrittenNodes;
	}


	private void writeNodes(ExternalGraphWriter<N,E> writer, Set<String> nodesToWrite, Dag<N,E> dag) {
		for(String nodeKey : nodesToWrite) {

			N node = dag.findNode(nodeKey);

			if(node != null) {
				writer.writeNode(node);
			}
		}
	}

}
