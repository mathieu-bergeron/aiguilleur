package ca.ntro.core.reflection;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathName;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectGraphJdk extends GenericGraphNtro<DirectedGraphSearchOptions, ObjectValue, ReferenceValue> implements ObjectGraph {
	
	private Object rootObject;

	private Map<Object, Map<Node<ObjectValue>, Object>> localHeap = new HashMap<>();
	
	public ObjectGraphJdk(Object rootObject) {
		this.rootObject = rootObject;
	}

	@Override
	public GraphId id() {
		return GraphId.fromGraphName(label());
	}

	@Override
	public String label() {
		return rootObject.getClass().getSimpleName();
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions(new Direction[] {Direction.FORWARD});
	}

	@Override
	protected <R> void _reduceRootNodes(ResultNtro<R> result, NodeReducer<ObjectValue, R> reducer) {
		
			try {

				result.registerValue(reducer.reduceNode(result.value(), createNode(Path.rootPath(), rootObject)));

			} catch (Throwable t) {

				result.registerException(t);
			}
	}

	@Override
	protected <R> void _reduceNextEdges(Node<ObjectValue> fromNode, 
							           Direction direction,
							           ResultNtro<R> result,
			                           ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		
		if(result.hasException()) {
			return;
		}

		if(direction != Direction.FORWARD) {
			return;
		}

		Object currentObject = fromNode.value().object();

		for(Method method: currentObject.getClass().getMethods()) {
			
			if(method.getName().startsWith("get")
					&& method.getName().length() > 3
					&& !method.getName().equals("getClass")) {
				
				String rawAttributeName = method.getName().substring(3);
				String attributeName = rawAttributeName.substring(0,1).toLowerCase();
				attributeName += rawAttributeName.substring(1);
				
				try {

					Object attributeValue = method.invoke(currentObject);
					Path attributePath = Path.fromPath(fromNode.id().toFilepath());
					attributePath.addName(attributeName);
					
					Node<ObjectValue> toNode = createNode(attributePath, attributeValue);
					Edge<ReferenceValue> edge = createEdge(fromNode.id(), attributeName, toNode.id());
					
					List<Edge<ReferenceValue>> walkedEdges = new ArrayList<>();
					walkedEdges.add(edge);

					result.registerValue(reducer.reduceReachableEdge(result.value(), walkedEdges, fromNode, edge, toNode));
					
				} catch (Break e) {

					break;

				} catch (Throwable t) {
					
					result.registerException(t);
					break;
				}
			}
		}
	}

	private Edge<ReferenceValue> createEdge(NodeId fromId, String attributeName, NodeId toId){

		ReferenceValue referenceValue = new ReferenceValue(attributeName);

		EdgeId edgeId = new EdgeId(fromId, new PathName(attributeName), toId);

		Edge<ReferenceValue> edge = new EdgeNtro<ReferenceValue>(edgeId, referenceValue);
		
		return edge;
	}
	
	private Node<ObjectValue> findNodeInLocalHeap(Object object) {
		
		Node<ObjectValue> node = null;
		
		Map<Node<ObjectValue>, Object> objectByNode = localHeap.get(object);
		
		for(Map.Entry<Node<ObjectValue>, Object> entry : objectByNode.entrySet()) {

			if(entry.getValue() == object) {
				node = entry.getKey();
				break;
			}
		}
		
		return node;
	}

	private void addNodeInLocalHeap(Node<ObjectValue> node) {
		
		Object object = node.value().object();

		Map<Node<ObjectValue>, Object> objectByNode = localHeap.get(object);

		if(objectByNode == null) {
			objectByNode = new HashMap<>();
			localHeap.put(object, objectByNode);
		}
		
		objectByNode.put(node, object);
	}

	private Node<ObjectValue> createNode(Path attributePath, Object object){
		
		Node<ObjectValue> node = findNodeInLocalHeap(object);
		
		if(node == null) {
			
			ObjectValue objectValue = new ObjectValue(object);
			NodeId nodeId = new NodeId(attributePath);

			node = new NodeNtro<ObjectValue>(nodeId, objectValue);
			
			addNodeInLocalHeap(node);
		}
		
		return node;
	}

}
