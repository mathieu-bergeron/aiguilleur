package ca.ntro.core.reflection;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.StepId;
import ca.ntro.core.graphs.StepIdNtro;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.StepNtro;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectGraphNtro extends GenericGraphNtro<ObjectValue, ReferenceValue, Node<ObjectValue>, Edge<ReferenceValue>> implements ObjectGraph {

	private Object[] startObjects;

	public ObjectGraphNtro(Object startObject) {
		this.startObjects = new Object[] {startObject};
	}

	public ObjectGraphNtro(Object[] startObjects) {
		this.startObjects = startObjects;
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

		for(Object rootObject : startObjects) {
			labelPath.addName(rootObject.getClass().getSimpleName());
		}

		return labelPath.toHtmlId();
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions(new Direction[] {Direction.FORWARD});
	}

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<ObjectValue, Node<ObjectValue>, R> reducer) {
		if(result.hasException()) {
			return;
		}

		if(startObjects.length == 1) {

			_reduceStartNode(result, Path.emptyPath(), startObjects[0], reducer);

		}else {

			for(int i = 0; i < startObjects.length; i++) {

				if(result.hasException()) {
					return;
				}
				
				Object object = startObjects[i];

				Path objectPath = Path.fromSingleName(String.valueOf(i));
				objectPath.addName(object.getClass().getSimpleName());
				
				_reduceStartNode(result, objectPath, object, reducer);
			}
		}
	}

	private <R> void _reduceStartNode(ResultNtro<R> result, Path objectPath, Object object, NodeReducer<ObjectValue,Node<ObjectValue>, R> reducer) {
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
	protected <R> void _reduceNextStepIds(Node<ObjectValue> fromNode, 
			                              ResultNtro<R> result, 
			                              EdgeNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}

		Object currentObject = fromNode.value().object();
		
		_reduceMethodNames(currentObject, result, (__, methodName) -> {

			if(isGetterName(methodName) && isUserDefinedMethod(currentObject, methodName)) {
				
				String attributeName = attributeNameFromGetterName(methodName);

				try {

					result.registerValue(reducer.reduceStep(result.value(), new StepIdNtro(Direction.FORWARD, attributeName)));

				} catch (Throwable t) {
					
					result.registerException(t);
				}
			}

			return result.value();
		});
	}
	


	private boolean isUserDefinedMethod(Object object, String methodName) {
		boolean isUserDefined = true;
		
		if(methodName.equals("getClass")) {

			isUserDefined = false;
			
		} else if(object instanceof String && 
				(methodName.equals("getChars")
						|| methodName.equals("getBytes"))) {
			
			isUserDefined = false;

		}
		
		
		
		return isUserDefined;
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
	protected <R> void _reduceNextStepsById(Node<ObjectValue> fromNode, 
								            StepId stepId,
			                                ResultNtro<R> result, 
			                                StepReducer<ObjectValue, ReferenceValue, Node<ObjectValue>, Edge<ReferenceValue>, R> reducer) {

		if(result.hasException()) {
			return;
		}

		if(stepId.direction() != Direction.FORWARD) {
			return;
		}

		Object currentObject = fromNode.value().object();
		
		String edgeName = stepId.name().toString();
		
		String getterName = getterNameFromAttributeName(edgeName);
		
		try {

			Object attributeValue = invokeGetter(currentObject, getterName);
			
			Path attributePath = Path.fromRawPath(fromNode.id().toString());
			attributePath.addName(edgeName);
			
			Node<ObjectValue> toNode = createNode(attributePath, attributeValue);
			Edge<ReferenceValue> edge = createEdge(fromNode.id(), edgeName, toNode.id());

			Step<ObjectValue, ReferenceValue, Node<ObjectValue>, Edge<ReferenceValue>> walkedStep = new StepNtro<>(Direction.FORWARD, fromNode, edge, toNode);

			result.registerValue(reducer.reduceWalkedStep(result.value(), walkedStep));
			
		} catch (Throwable t) {
			
			result.registerException(t);

		} 
	}
	
	private Edge<ReferenceValue> createEdge(NodeId fromId, String attributeName, NodeId toId){

		ReferenceValue referenceValue = new ReferenceValue(attributeName);

		EdgeName edgeId = new EdgeName(fromId, new Key(attributeName), toId);

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
