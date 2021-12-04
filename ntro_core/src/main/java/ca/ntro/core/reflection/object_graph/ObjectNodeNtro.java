package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectNodeNtro 

	   extends        NodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>

	   implements     ObjectNode {
	
	private Object object;
	private LocalHeap localHeap;
	private ObjectGraph graph;

	public ObjectGraph getGraph(){
		return graph;
	}

	public void setGraph(ObjectGraph graph) {
		this.graph = graph;
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

	public Object getObject() {
		return object;
	}

	public ObjectNodeNtro(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId) {
		super(nodeId);
		setGraph(graph);
		setLocalHeap(localHeap);
		setObject(object);
	}

	protected abstract <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;

	@Override
	public GenericGraph<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected <R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
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
	protected <R> void _reduceEdgesByType(EdgeType edgeType, 
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
			
			Path attributePath = Path.fromRawPath(this.id().toKey().toString());
			attributePath.addName(attributeName);
			
			ObjectNode toNode = getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue);
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

	@Override
	public boolean isStartNode() {
		return ((ObjectGraphNtro) getGraph()).isStartNode(this);
	}
}
