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

	public <R> void reduceEdgesByTypeForList(EdgeType edgeType, 
			                                 ResultNtro<R> result, 
			                                 EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer,
			                                 List<?> list) {

		String attributeName = edgeType.name().toString();
		
		Integer index = Integer.parseInt(attributeName);

		Object attributeValue = list.get(index);
		
		reduceAttributeEdge(result, reducer, attributeName, attributeValue);

	}

	public <R> void reduceAttributeEdge(ResultNtro<R> result, 
			                            EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer,
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
			                                EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer,
			                                Map<String,?> map) {

		String attributeName = edgeType.name().toString();

		Object attributeValue = map.get(attributeName);
		
		reduceAttributeEdge(result, reducer, attributeName, attributeValue);

	}

	public <R> void reduceEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                              ResultNtro<R> result, 
			                                              EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer,
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
	public <R> void reduceEdgesByType(EdgeType edgeType, 
			                          ResultNtro<R> result, 
			                          EdgeReducer<ObjectNode, ReferenceEdge, DirectedSearchOptions, R> reducer) {

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
