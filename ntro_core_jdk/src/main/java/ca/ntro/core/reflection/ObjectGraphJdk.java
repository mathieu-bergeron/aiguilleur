package ca.ntro.core.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.wrappers.result.ResultNtro;


public class ObjectGraphJdk extends ObjectGraphNtro  implements ObjectGraph {
	
	private Map<Object, Map<Node<ObjectValue>, Object>> localHeap = new HashMap<>();
	
	public ObjectGraphJdk(Object rootObject) {
		super(rootObject);
	}

	public ObjectGraphJdk(Object[] rootObjects) {
		super(rootObjects);
	}

	@Override
	protected Node<ObjectValue> findNodeInLocalHeap(Object object) {
		
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

	@Override
	protected void addNodeInLocalHeap(Node<ObjectValue> node) {
		
		Object object = node.value().object();

		Map<Node<ObjectValue>, Object> objectByNode = localHeap.get(object);

		if(objectByNode == null) {
			objectByNode = new HashMap<>();
			localHeap.put(object, objectByNode);
		}
		
		objectByNode.put(node, object);
	}

	@Override
	protected <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(Method method : object.getClass().getMethods()) {
			
			try {

				result.registerValue(reducer.reduceMethodName(result.value(), method.getName()));

			} catch (Throwable e) {
				
				result.registerException(e);
				break;
			}
		}
	}
	
	@Override
	protected Object invokeGetter(Object object, String getterName) throws Throwable {

		Method method = object.getClass().getMethod(getterName);

		Object returnValue = method.invoke(object);

		return returnValue;
	}
}
