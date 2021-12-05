package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectNodeStructureNtro implements ObjectNodeStructure {
	
	private ObjectNode node;
	private ObjectGraphStructure graphStructure;

	public ObjectNode getNode() {
		return node;
	}

	public void setNode(ObjectNode node) {
		this.node = node;
	}

	public ObjectGraphStructure getGraphStructure() {
		return graphStructure;
	}

	public void setGraphStructure(ObjectGraphStructure graphStructure) {
		this.graphStructure = graphStructure;
	}

	protected abstract <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;
	
	private Object object() {
		return getNode().object();
	}

	@Override
	public <R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		if(direction != Direction.FORWARD) {
			return;
		}
		
		if(isSimpleValue(object())) {
			return;
		}

		Object currentObject = object();

		if(currentObject instanceof List) {
			
			_reduceEdgeTypesForList(result, reducer, (List<?>) currentObject);

			
		} else if(currentObject instanceof Map) {

			_reduceEdgeTypesForMap(result, reducer, (Map<String,?>) currentObject);
			
		}else {
			
			_reduceEdgeTypesForUserDefinedObject(result, reducer, currentObject);
		}
	}

	@Override
	public <R> void reduceEdgesByType(EdgeType edgeType, 
			                          ResultNtro<R> result, 
			                          EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {

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
			
			Path attributePath = Path.fromRawPath(this.asNode().id().toKey().toString());
			attributePath.addName(attributeName);
			
			ObjectNode toNode = ((ObjectGraphStructureNtro)getGraphStructure()).getLocalHeap().findOrCreateNode(getGraphStructure(), attributePath, attributeValue);
			ReferenceEdge edge = new ReferenceEdgeNtro(this.asNode(), attributeName, toNode);
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 
		// TODO Auto-generated method stub
		
	}

	private boolean isSimpleValue(Object object) {
		return object instanceof String
				|| object instanceof Character
				|| object instanceof Integer
				|| object instanceof Long
				|| object instanceof Float
				|| object instanceof Double
				|| object instanceof Boolean;
	}

	protected <R> void _reduceEdgeTypesForList(ResultNtro<R> result, 
			                                   EdgeTypeReducer<R> reducer, 
			                                   List<?> list) {
		
		for(int i = 0; i < list.size(); i++) {
			try {
				
				result.registerValue(reducer.reduceEdgeType(result.value(), new EdgeTypeNtro(Direction.FORWARD, String.valueOf(i))));

			} catch (Throwable t) {
				
				result.registerException(t);
			}
		}
	}

	protected <R> void _reduceEdgeTypesForMap(ResultNtro<R> result, 
			                                  EdgeTypeReducer<R> reducer, 
			                                  Map<String,?> map) {
		
		List<String> keys = new ArrayList<>(map.keySet());
		
		List<String> sortedKeys = Ntro.collections().sortList(keys);

		for(String key : sortedKeys) {
			try {
				
				result.registerValue(reducer.reduceEdgeType(result.value(), new EdgeTypeNtro(Direction.FORWARD, key)));

			} catch (Throwable t) {
				
				result.registerException(t);
			}
		}
	}

	protected <R> void _reduceEdgeTypesForUserDefinedObject(ResultNtro<R> result, 
			                                                EdgeTypeReducer<R> reducer, 
			                                                Object currentObject) {

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

	private boolean isStartNode() {
		return asNode().isStartNode();
	}
}
