package ca.ntro.core.reflection;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
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
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectGraphJdk extends GenericGraphNtro<DirectedGraphSearchOptions, ObjectValue, ReferenceValue> implements ObjectGraph {
	
	private Object rootObject;
	
	// XXX: must make sure the same object gets the same id
	private Map<String, Object> localHeap = new HashMap<>();
	
	
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
	public <R> Result<R> reduceRootNodes(R initialValue, NodeReducer<ObjectValue, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
			try {

				result.registerValue(reducer.reduceNode(result.value(), createNode(Path.rootPath(), rootObject)));

			} catch (Throwable t) {

				result.registerException(t);
			}
		
		return result;
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<ObjectValue> fromNode, 
			                             R initialValue, 
			                             ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
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
					
					NodeNtro<ObjectValue> toNode = createNode(attributePath, attributeValue);
					EdgeNtro<ReferenceValue> edge = createEdge(fromNode.id(), attributeName, toNode.id());
					
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

		throw new RuntimeException("[FIXME] must maintain a localHeap here. A second visit to an object must result in the same NodeId.");
		//return result;
	}

	private EdgeNtro<ReferenceValue> createEdge(NodeId fromId, String attributeName, NodeId toId){

		ReferenceValue referenceValue = new ReferenceValue(attributeName);

		EdgeId edgeId = new EdgeId(fromId, new PathName(attributeName), toId);

		EdgeNtro<ReferenceValue> edge = new EdgeNtro<ReferenceValue>(edgeId, referenceValue);
		
		return edge;
	}

	private NodeNtro<ObjectValue> createNode(Path attributePath, Object attributeValue){

		ObjectValue objectValue = new ObjectValue(attributeValue);
		NodeId nodeId = new NodeId(attributePath);
		NodeNtro<ObjectValue> node = new NodeNtro<ObjectValue>(nodeId, objectValue);
		
		return node;
	}
	
	protected boolean isObjectAlreadyVisited(List<Object> visitedObjects, Object target) {
		boolean visited = false;
		
		for(Object candidate : visitedObjects) {
			if(candidate == target) {
				visited = true;
				break;
			}
		}
		
		return visited;
	}
}
