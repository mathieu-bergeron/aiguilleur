package ca.ntro.core.reflection.object_graph;


import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.directed_graph.DirectedNodeNtro;
import ca.ntro.core.graphs.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectNodeNtro 

	   extends        DirectedNodeNtro<ObjectNode, ReferenceEdge>

	   implements     GenericObjectNode, 
	                  ObjectNode,
	                  ObjectNodeSimpleValue {
	
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


	@Override
	public GenericGraph<ObjectNode, ReferenceEdge, DirectedSearchOptions> parentGraph() {
		return getGraph();
	}

	@Override
	public String label() {
		String label = "";
		
		if(isList()) {

			label = "List";

		}else if(isMap()) {

			label = "Map";

		}else if(isUserDefinedObject()) {
			
			label = type().getSimpleName();

		}else if(isSimpleValue()) {

			if(asSimpleValue().isNull()) {
				
				label = "null";

			} else if(asSimpleValue().isString()) {
				
				label = "\"" + getObject().toString() + "\"";

			}else {
				
				label = getObject().toString();
			}
		}
		
		return label;
	}

	@Override
	public Object object() {
		return getObject();
	}

	@Override
	public Class<?> type() {
		Class<?> type = null;
		Object currentObject = getObject();

		if(currentObject != null) {
			type = currentObject.getClass();
		}
		
		return type;
	}

	@Override
	public boolean isList() {
		return getObject() instanceof List;
	}


	@Override
	public boolean isMap() {
		return getObject() instanceof Map;
	}


	@Override
	public boolean isUserDefinedObject() {
		return !isList()
				&& !isMap()
				&& !isSimpleValue();
	}


	@Override
	public boolean isSimpleValue() {
		return isNull()
				|| isBoolean()
				|| isNumber()
				|| isString();
	}

	@Override
	public ObjectNodeSimpleValue asSimpleValue() {
		return (ObjectNodeSimpleValue) this;
	}
	

	@Override
	public boolean isNull() {
		return getObject() == null;
	}


	@Override
	public boolean isBoolean() {
		return getObject() instanceof Boolean;
	}


	@Override
	public boolean isNumber() {
		Object currentObject = getObject();
		return  currentObject instanceof Character
				|| currentObject instanceof Integer
				|| currentObject instanceof Long
				|| currentObject instanceof Float
				|| currentObject instanceof Double;
	}

	@Override
	public boolean isString() {
		return getObject() instanceof String;
	}

	@Override
	public boolean asBoolean() {
		return (Boolean) getObject();
	}

	@Override
	public double asNumber() {
		return Double.parseDouble(getObject().toString());
	}

	@Override
	public String asString() {
		return getObject().toString();
	}
	
	@Override
	public ObjectUpdates asUpdates() {
		// TODO: describe the object as a sequence
		// of SET/INSERT/DELETE operations
		return null;
	}

	@Override
	public List<?> asList() {
		return (List<?>) getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <I> List<I> asList(Class<I> itemClass) {
		return (List<I>) getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Map<String, V> asMap(Class<V> valueClass) {
		return (Map<String,V>) getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> asMap() {
		return (Map<String, ?>) getObject();
	}

	@Override
	public Object asUserDefinedObject() {
		return getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V asUserDefinedObject(Class<V> _class) {
		return (V) getObject();
	}

}
