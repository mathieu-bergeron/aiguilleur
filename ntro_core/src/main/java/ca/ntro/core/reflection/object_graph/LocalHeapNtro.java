package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.path.Path;

public abstract class LocalHeapNtro implements LocalHeap {

	@Override
	public ObjectNode findOrCreateNode(ObjectGraph graph, Path attributePath, Object object, boolean isStartNode) {

		ObjectNode node = findNodeInHeap(object);

		if(node == null) {
			
			// FIXME: SimpleValue(null)
			String className;
			if(object != null) {
				className = object.getClass().getSimpleName();
			}else {
				className = "null";
			}
			
			ObjectNodeId objectNodeId = new ObjectNodeIdNtro(attributePath.toKey(), className);

			node = createNode(graph, this, object, objectNodeId, isStartNode);

			addNodeToHeap(node);
		}
		
		return node;
	}

	protected abstract ObjectNode createNode(ObjectGraph graph, LocalHeap localHeap, Object object, ObjectNodeId nodeId, boolean isStartNode);

	protected abstract ObjectNode findNodeInHeap(Object object);

	protected abstract void addNodeToHeap(ObjectNode node);

}
