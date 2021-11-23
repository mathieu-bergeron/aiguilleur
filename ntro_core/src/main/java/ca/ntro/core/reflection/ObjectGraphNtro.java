package ca.ntro.core.reflection;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathName;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectGraphNtro extends GenericGraphNtro<ObjectValue, ReferenceValue> implements ObjectGraph {

	private Object[] rootObjects;

	public ObjectGraphNtro(Object rootObject) {
		this.rootObjects = new Object[] {rootObject};
	}

	public ObjectGraphNtro(Object[] rootObjects) {
		this.rootObjects = rootObjects;
	}

	protected abstract Node<ObjectValue> findNodeInLocalHeap(Object object);
	protected abstract void addNodeInLocalHeap(Node<ObjectValue> node);

	protected abstract <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;

	@Override
	public GraphId id() {
		return GraphId.fromGraphName(label());
	}

	@Override
	public String label() {
		Path labelPath = Path.emptyPath();

		for(Object rootObject : rootObjects) {
			labelPath.addName(rootObject.getClass().getSimpleName());
		}

		return labelPath.toHtmlId();
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions(new Direction[] {Direction.FORWARD});
	}

	@Override
	protected <R> void _reduceRootNodes(ResultNtro<R> result, NodeReducer<ObjectValue, R> reducer) {
		if(result.hasException()) {
			return;
		}

		if(rootObjects.length == 1) {

			_reduceRootNode(result, Path.emptyPath(), rootObjects[0], reducer);

		}else {

			for(int i = 0; i < rootObjects.length; i++) {

				if(result.hasException()) {
					return;
				}
				
				Object object = rootObjects[i];

				Path objectPath = Path.fromSingleName(String.valueOf(i));
				objectPath.addName(object.getClass().getSimpleName());
				
				_reduceRootNode(result, objectPath, object, reducer);
			}
		}
	}

	private <R> void _reduceRootNode(ResultNtro<R> result, Path objectPath, Object object, NodeReducer<ObjectValue, R> reducer) {
		if(result.hasException()) {
			return;
		}

		try {

			result.registerValue(reducer.reduceNode(result.value(), createNode(objectPath, object)));

		} catch (Throwable t) {

			result.registerException(t);
		}
	}

	@Override
	protected <R> void _reduceNextEdgeNames(Node<ObjectValue> fromNode, 
			                                Direction direction, 
			                                ResultNtro<R> result, 
			                                EdgeNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}

		if(direction != Direction.FORWARD) {
			return;
		}

		Object currentObject = fromNode.value().object();
		
		_reduceMethodNames(currentObject, result, (__, methodName) -> {

			if(isGetterName(methodName) && !methodName.equals("getClass")) {
				
				String attributeName = attributeNameFromGetterName(methodName);

				try {

					result.registerValue(reducer.reduceEdgeName(result.value(), attributeName));

				} catch (Throwable t) {
					
					result.registerException(t);
				}
			}

			return null;
		});
	}
	


	private boolean isGetterName(String methodName) {
		return methodName.startsWith("get")
				&& methodName.length() > 3;
	}


	protected String attributeNameFromGetterName(String methodName) {

		String rawAttributeName = methodName.substring(3);
		String attributeName = rawAttributeName.substring(0,1).toLowerCase();
		attributeName += rawAttributeName.substring(1);
		
		return attributeName;
	}

	protected String getterNameFromAttributeName(String attributeName) {
		
		String getterName = "get";
		
		getterName += attributeName.substring(0,1).toUpperCase();
		getterName += attributeName.substring(1);
		
		return getterName;
	}
	

	@Override
	protected <R> void _reduceNextEdgesByName(Node<ObjectValue> fromNode, 
			                                  Direction direction, 
			                                  String edgeName, 
			                                  ResultNtro<R> result, 
			                                  ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		if(result.hasException()) {
			return;
		}

		if(direction != Direction.FORWARD) {
			return;
		}

		Object currentObject = fromNode.value().object();
		
		String getterName = getterNameFromAttributeName(edgeName);
		
		try {

			Object attributeValue = invokeGetter(currentObject, getterName);
			
			Path attributePath = Path.fromPath(fromNode.id().toFilepath());
			attributePath.addName(edgeName);
			
			Node<ObjectValue> toNode = createNode(attributePath, attributeValue);
			Edge<ReferenceValue> edge = createEdge(fromNode.id(), edgeName, toNode.id());
			
			List<Edge<ReferenceValue>> walkedEdges = new ArrayList<>();
			walkedEdges.add(edge);

			result.registerValue(reducer.reduceReachableEdge(result.value(), walkedEdges, fromNode, edge, toNode));
			
		} catch (Throwable t) {
			
			result.registerException(t);

		} 
	}
	
	private Edge<ReferenceValue> createEdge(NodeId fromId, String attributeName, NodeId toId){

		ReferenceValue referenceValue = new ReferenceValue(attributeName);

		EdgeId edgeId = new EdgeId(fromId, new PathName(attributeName), toId);

		Edge<ReferenceValue> edge = new EdgeNtro<ReferenceValue>(edgeId, referenceValue);
		
		return edge;
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
