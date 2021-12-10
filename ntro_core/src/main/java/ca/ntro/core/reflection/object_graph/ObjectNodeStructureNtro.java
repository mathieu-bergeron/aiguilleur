package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.EdgeReducer;
import ca.ntro.core.graphs.generics.graph.EdgeTypeReducer;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectNodeStructureNtro implements ObjectNodeStructure {
	
	private ObjectNodeNtro node;
	private ObjectGraphNtro graph;

	public ObjectNodeNtro getNode() {
		return node;
	}

	public void setNode(ObjectNodeNtro node) {
		this.node = node;
	}

	public ObjectGraphNtro getGraph() {
		return graph;
	}

	public void setGraph(ObjectGraphNtro graph) {
		this.graph = graph;
	}

	public ObjectNodeStructureNtro(ObjectNodeNtro node, ObjectGraphNtro graph) {
		setNode(node);
		setGraph(graph);
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
		
		if(currentObject == null) {
			
			// TODO: simpleValue(null)
			
		}else if(currentObject instanceof List) {
			
			_reduceEdgeTypesForList(result, reducer, (List<?>) currentObject);

			
		} else if(currentObject instanceof Map) {

			_reduceEdgeTypesForMap(result, reducer, (Map<String,?>) currentObject);
			
		}else {
			
			_reduceEdgeTypesForUserDefinedObject(result, reducer, currentObject);
		}
	}

	public <R> void reduceEdgesByTypeForList(EdgeType edgeType, 
			                                 ResultNtro<R> result, 
			                                 EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer) {

		List<?> currentObject = (List<?>) object();
		
		String indexName = edgeType.name().toString();
		
		Integer index = Integer.parseInt(indexName);
		
		try {

			Object attributeValue = currentObject.get(index);
			
			Path attributePath = Path.fromRawPath(this.asNode().id().toKey().toString());
			attributePath.addName(indexName);
			
			assert(getGraph() != null);
			
			ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
			
			ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
			ReferenceEdge edge = new ReferenceEdgeNtro(this.asNode(), indexName, toNode);
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 

	}

	public <R> void reduceEdgesByTypeForMap(EdgeType edgeType, 
			                                ResultNtro<R> result, 
			                                EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer) {

		Map<String, ?> currentObject = (Map<String, ?>) object();
		
		String keyName = edgeType.name().toString();
		
		try {

			Object attributeValue = currentObject.get(keyName);
			
			Path attributePath = Path.fromRawPath(this.asNode().id().toKey().toString());
			attributePath.addName(keyName);
			
			assert(getGraph() != null);
			
			ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
			
			ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
			ReferenceEdge edge = new ReferenceEdgeNtro(this.asNode(), keyName, toNode);
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 

	}

	public <R> void reduceEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                              ResultNtro<R> result, 
			                                              EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer) {

		Object currentObject = object();
		
		String attributeName = edgeType.name().toString();
		
		String getterName = ReflectionUtils.getterNameFromAttributeName(attributeName);
		
		try {

			Object attributeValue = invokeGetter(currentObject, getterName);
			
			Path attributePath = Path.fromRawPath(this.asNode().id().toKey().toString());
			attributePath.addName(attributeName);
			
			assert(getGraph() != null);
			
			ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
			
			ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
			ReferenceEdge edge = new ReferenceEdgeNtro(this.asNode(), attributeName, toNode);
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 

	}

	@Override
	public <R> void reduceEdgesByType(EdgeType edgeType, 
			                          ResultNtro<R> result, 
			                          EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer) {

		if(result.hasException()) {
			return;
		}

		if(edgeType.direction() != Direction.FORWARD) {
			return;
		}

		Object currentObject = object();

		if(currentObject == null) {
			
			// TODO: simpleValue(null)
		
		} else if(currentObject instanceof List) {
			
			reduceEdgesByTypeForList(edgeType, result, reducer);

			
		} else if(currentObject instanceof Map) {

			reduceEdgesByTypeForMap(edgeType, result, reducer);

			
		}else {

			reduceEdgesByTypeForUserDefinedObject(edgeType, result, reducer);
		}
		
		
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

	@Override
	public boolean isStartNode() {
		return asNode().isStartNode();
	}

	/*
	@Override
	public String label() {
		return getObject().getClass().getSimpleName();
	}
	*/

	
	
	
}
