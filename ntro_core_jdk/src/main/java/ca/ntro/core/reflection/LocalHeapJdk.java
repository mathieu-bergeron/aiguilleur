package ca.ntro.core.reflection;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.LocalHeapNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectNode;

public class LocalHeapJdk extends LocalHeapNtro {

	private Map<Object, Map<ObjectNode, Object>> heap = new HashMap<>();

	@Override
	protected ObjectNode createNode(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId) {
		
		return new ObjectNodeJdk(graph, localHeap, object, nodeId);
	}

	@Override
	protected ObjectNode findNodeInHeap(Object object) {

		ObjectNode node = null;
		
		Map<ObjectNode, Object> objectByNode = heap.get(object);
		
		if(objectByNode != null) {

			for(Map.Entry<ObjectNode, Object> entry : objectByNode.entrySet()) {

				if(entry.getValue() == object) {
					node = entry.getKey();
					break;
				}
			}
		}
		
		return node;
	}

	@Override
	protected void addNodeToHeap(ObjectNode node) {

		Object object = node.object();

		Map<ObjectNode, Object> objectByNode = heap.get(object);

		if(objectByNode == null) {
			objectByNode = new HashMap<>();
			heap.put(object, objectByNode);
		}

		objectByNode.put(node, object);
	}


}
