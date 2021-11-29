package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectNodeNtro 

	   extends        NodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements     ObjectNode {
	
	private Object object;
	private LocalHeap localHeap;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public LocalHeap getLocalHeap() {
		return localHeap;
	}

	public void setLocalHeap(LocalHeap localHeap) {
		this.localHeap = localHeap;
	}

	public ObjectNodeNtro(LocalHeap localHeap, Object object, NodeId nodeId) {
		super(nodeId);
		setLocalHeap(localHeap);
		setObject(object);
	}

	protected abstract <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;

	@Override
	protected <R> void _reduceEdgeTypes(ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}

		Object currentObject = object();
		
		_reduceMethodNames(currentObject, result, (__, methodName) -> {

			if(ReflectionUtils.isGetterName(methodName) 
					&& ReflectionUtils.isUserDefinedMethod(currentObject, methodName)) {
				
				String attributeName = ReflectionUtils.attributeNameFromGetterName(methodName);

				try {

					result.registerValue(reducer.reduceEdgeType(result.value(), new EdgeTypeNtro(Direction.FORWARD, attributeName)));

				} catch (Throwable t) {
					
					result.registerException(t);
				}
			}

			return result.value();
		});
	}

	@Override
	protected <R> void _reduceEdgesByType(EdgeType edgeType, 
			                              ResultNtro<R> result, 
			                              EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {

		if(result.hasException()) {
			return;
		}

		if(edgeType.direction() != Direction.FORWARD) {
			return;
		}

		Object currentObject = object();
		
		String attributeName = edgeType.name().toString();
		
		String getterName = ReflectionUtils.getterNameFromAttributeName(attributeName);
		
		try {

			Object attributeValue = invokeGetter(currentObject, getterName);
			
			Path attributePath = Path.fromRawPath(this.id().toKey().toString());
			attributePath.addName(attributeName);
			
			ObjectNode toNode = getLocalHeap().findOrCreateNode(attributePath, attributeValue);
			ReferenceEdge edge = new ReferenceEdgeNtro(this, attributeName, toNode);
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 
	}

	@Override
	public Object object() {
		return getObject();
	}
}