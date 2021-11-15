package ca.ntro.core.graphs.writers;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.Node;

public abstract class DagWriterNtro<N extends Node, E extends Edge> implements DagWriter<N,E> {

	protected abstract void writeEdge(N from, E edge, N to);
	protected abstract void writeNode(N node);

	public void write(Dag<N, E> dag) {

		Set<String> unwrittenNodes = writeEdges(dag);

		writeNodes(unwrittenNodes, dag);
	}

	private Set<String> writeEdges(Dag<N,E> dag) {
		
		Set<String> unwrittenNodes = dag.reduceNodes(new HashSet<String>(), (accumulator, n) -> {
			
			accumulator.add(n.id().toKey());

			return accumulator;

		}).value();
		
		dag.forEachEdge((from, edge, to) -> {

			unwrittenNodes.remove(from.id().toKey());
			unwrittenNodes.remove(to.id().toKey());

			writeEdge(from, edge, to);
		});
		
		return unwrittenNodes;
	}


	private void writeNodes(Set<String> nodesToWrite, Dag<N,E> dag) {
		for(String nodeKey : nodesToWrite) {

			N node = dag.findNode(nodeKey);

			if(node != null) {
				writeNode(node);
			}
		}
	}

}
