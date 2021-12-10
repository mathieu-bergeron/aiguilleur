package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.path.Path;

public abstract class LocalHeapNtro implements LocalHeap {

	@Override
	public ObjectNode findOrCreateNode(ObjectGraph graph, Path attributePath, Object object, boolean isStartNode) {

		ObjectNode node;
		
		if(object == null) {

			node = createNode(graph, attributePath, object, isStartNode);

		}else {

			node = findNodeInHeap(object);

			if(node == null) {

			    node = createNode(graph, attributePath, object, isStartNode);

				addNodeToHeap(node);
			}
		}
		
		return node;
	}

	private ObjectNode createNode(ObjectGraph graph, Path attributePath, Object object, boolean isStartNode) {
		ObjectNode node;

		NodeId nodeId = new NodeIdNtro(attributePath.toKey());
		node = createNode(graph, this, object, nodeId, isStartNode);

		return node;
	}

	protected abstract ObjectNode createNode(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId, boolean isStartNode);

	protected abstract ObjectNode findNodeInHeap(Object object);

	protected abstract void addNodeToHeap(ObjectNode node);

}
