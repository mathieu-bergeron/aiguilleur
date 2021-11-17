package ca.ntro.core.graphs.writers;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.Node;
import ca.ntro.core.graphs.generic_graph.NodeValue;
import ca.ntro.core.identifyers.StorageId;

public abstract class DagWriterNtro<NV extends NodeValue, EV extends EdgeValue> implements DagWriter<NV,EV> {

	protected abstract ExternalGraphWriter<NV, EV> createExeternalGraphWriter(StorageId storageId);
	
	public void write(Dag<NV, EV> dag) {
		
		ExternalGraphWriter<NV,EV> writer = createExeternalGraphWriter(dag.id());
		
		write(writer, dag);
	}
	
	protected void write(ExternalGraphWriter<NV,EV> writer, Dag<NV,EV> dag) {

		Set<String> unwrittenNodes = writeEdges(writer, dag);

		writeNodes(writer, unwrittenNodes, dag);
		
		writer.writeDot();
		writer.writePng();
	}

	protected Set<String> writeEdges(ExternalGraphWriter<NV,EV> writer, Dag<NV,EV> dag) {
		
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

	protected void writeNodes(ExternalGraphWriter<NV,EV> writer, Set<String> nodesToWrite, Dag<NV,EV> dag) {
		for(String nodeKey : nodesToWrite) {

			Node<NV> node = dag.findNode(nodeKey);

			if(node != null) {
				writer.writeNode(node);
			}
		}
	}

}
