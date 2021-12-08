package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.path.Path;

public abstract class LocalHeapNtro implements LocalHeap {

	@Override
	public ObjectNode findOrCreateNode(ObjectGraph graph, Path attributePath, Object object) {

		ObjectNode node = findNodeInHeap(object);

		if(node == null) {
			
			NodeId nodeId = new NodeIdNtro(attributePath.toKey());

			node = createNode(graph, this, object, nodeId);

			addNodeToHeap(node);
		}
		
		return node;
	}

	protected abstract ObjectNode createNode(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId);

	protected abstract ObjectNode findNodeInHeap(Object object);

	protected abstract void addNodeToHeap(ObjectNode node);

}
