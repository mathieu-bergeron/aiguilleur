package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.graph.EdgeReducer;
import ca.ntro.core.graphs.generics.graph.EdgeTypeReducer;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
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
	protected abstract Stream<String> methodNames(Object object);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;

	@Override
	public Stream<EdgeType> edgeTypes(Direction direction) {
		return new StreamNtro<EdgeType>() {
			@Override
			public void _forEach(Visitor<EdgeType> visitor) throws Throwable {
				if(direction != Direction.FORWARD) {
					return;
				}
				
				if(node().isList()) {
					
					_forEachEdgeTypeList(visitor, node.asList());
					
				} else if(node().isMap()) {

					_forEachEdgeTypeMap(visitor, (Map<String,?>) node.asMap());
					
				}else if(node().isUserDefinedObject()){
					
					_forEachEdgeTypeUserDefinedObject(visitor, node.asUserDefinedObject());
				}
			}

		};
	}

	protected void _forEachEdgeTypeList(Visitor<EdgeType> visitor, List<?> list) throws Throwable {
		for(int i = 0; i < list.size(); i++) {
			visitor.visit(new EdgeTypeNtro(Direction.FORWARD, String.valueOf(i)));
		}
	}

	protected void _forEachEdgeTypeMap(Visitor<EdgeType> visitor, Map<String, ?> map) throws Throwable {
		for(String key : map.keySet()) {
			visitor.visit(new EdgeTypeNtro(Direction.FORWARD, key));
		}
		
	}
	
	protected void _forEachEdgeTypeUserDefinedObject(Visitor<EdgeType> visitor, Object object) throws Throwable {
		methodNames(object)._forEach(methodName -> {

			if(ReflectionUtils.isGetterName(methodName) 
					&& ReflectionUtils.isUserDefinedMethod(object, methodName)) {
				
				String attributeName = ReflectionUtils.attributeNameFromGetterName(methodName);

				visitor.visit(new EdgeTypeNtro(Direction.FORWARD, attributeName));
			}
		});
	}


	@Override
	public <R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		if(direction != Direction.FORWARD) {
			return;
		}
		
		if(node().isList()) {
			
			_reduceEdgeTypesForList(result, reducer, node.asList());

			
		} else if(node().isMap()) {

			_reduceEdgeTypesForMap(result, reducer, (Map<String,?>) node.asMap());
			
		}else if(node().isUserDefinedObject()){
			
			_reduceEdgeTypesForUserDefinedObject(result, reducer, node.asUserDefinedObject());
		}
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
	public Stream<ReferenceEdge> edges(EdgeType edgeType) {
		return new StreamNtro<ReferenceEdge>(){

			@Override
			public void _forEach(Visitor<ReferenceEdge> visitor) throws Throwable {
				if(edgeType.direction() != Direction.FORWARD) {
					return;
				}

				if(node().isList()) {
					
					_forEachEdgeByTypeList(edgeType, node().asList(), visitor);

					
				} else if(node().isMap()) {

					_forEachEdgeByTypeMap(edgeType, node().asMap(), visitor);

					
				}else if(node().isUserDefinedObject()){

					_forEachEdgeByTypeUserDefined(edgeType, node().asUserDefinedObject(), visitor);
				}
				
			}
		};
	}

	protected void _forEachEdgeByTypeList(EdgeType edgeType, 
			                              List<?> list, 
			                              Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		Integer index = Integer.parseInt(attributeName);

		Object attributeValue = list.get(index);
		
		_visitAttributeEdge(attributeName, attributeValue, visitor);
	}

	protected void _visitAttributeEdge(String attributeName, Object attributeValue, Visitor<ReferenceEdge> visitor) throws Throwable {

		Path attributePath = Path.fromRawPath(this.node().id().toKey().toString());
		attributePath.addName(attributeName);
		
		ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
		
		ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
		ReferenceEdge edge = new ReferenceEdgeNtro(this.node(), attributeName, toNode);
		
		visitor.visit(edge);
	}

	protected void _forEachEdgeByTypeMap(EdgeType edgeType, 
			                             Map<String, ?> map, 
			                             Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		Object attributeValue = map.get(attributeName);
		
		_visitAttributeEdge(attributeName, attributeValue, visitor);
		
	}

	protected void _forEachEdgeByTypeUserDefined(EdgeType edgeType, 
			                                     Object object, 
			                                     Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		String getterName = ReflectionUtils.getterNameFromAttributeName(attributeName);

		Object attributeValue;

		attributeValue = invokeGetter(object, getterName);

		_visitAttributeEdge(attributeName, attributeValue, visitor);
	}


	@Override
	public <R> void reduceEdgesByType(EdgeType edgeType, 
			                          ResultNtro<R> result, 
			                          EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {

		if(result.hasException()) {
			return;
		}

		if(edgeType.direction() != Direction.FORWARD) {
			return;
		}

		if(node().isList()) {
			
			reduceEdgesByTypeForList(edgeType, result, reducer, node().asList());

			
		} else if(node().isMap()) {

			reduceEdgesByTypeForMap(edgeType, result, reducer, node().asMap());

			
		}else if(node().isUserDefinedObject()){

			reduceEdgesByTypeForUserDefinedObject(edgeType, result, reducer, node().asUserDefinedObject());
		}
	}

	public <R> void reduceEdgesByTypeForList(EdgeType edgeType, 
			                                 ResultNtro<R> result, 
			                                 EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer,
			                                 List<?> list) {

		String attributeName = edgeType.name().toString();
		
		Integer index = Integer.parseInt(attributeName);

		Object attributeValue = list.get(index);
		
		reduceAttributeEdge(result, reducer, attributeName, attributeValue);

	}

	public <R> void reduceAttributeEdge(ResultNtro<R> result, 
			                            EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer,
			                            String attributeName,
			                            Object attributeValue) {

		Path attributePath = Path.fromRawPath(this.node().id().toKey().toString());
		attributePath.addName(attributeName);
		
		ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
		
		ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
		ReferenceEdge edge = new ReferenceEdgeNtro(this.node(), attributeName, toNode);

		try {
			
			result.registerValue(reducer.reduceEdge(result.value(), edge));
			
		} catch (Throwable t) {
			
			result.registerException(t);
		} 

	}
	
	

	public <R> void reduceEdgesByTypeForMap(EdgeType edgeType, 
			                                ResultNtro<R> result, 
			                                EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer,
			                                Map<String,?> map) {

		String attributeName = edgeType.name().toString();

		Object attributeValue = map.get(attributeName);
		
		reduceAttributeEdge(result, reducer, attributeName, attributeValue);

	}

	public <R> void reduceEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                              ResultNtro<R> result, 
			                                              EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer,
			                                              Object currentObject) {

		String attributeName = edgeType.name().toString();
		
		String getterName = ReflectionUtils.getterNameFromAttributeName(attributeName);

		Object attributeValue;

		try {

			attributeValue = invokeGetter(currentObject, getterName);

			reduceAttributeEdge(result, reducer, attributeName, attributeValue);

		} catch (Throwable e) {
			
			result.registerException(e);
		}
	}


	@Override
	public boolean isStartNode() {
		return node().isStartNode();
	}


	@Override
	public ObjectNode node() {
		return getNode();
	}

	@Override
	public ObjectGraph parentGraph() {
		return getGraph();
	}
	
}
