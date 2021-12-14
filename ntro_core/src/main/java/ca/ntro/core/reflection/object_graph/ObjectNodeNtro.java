package ca.ntro.core.reflection.object_graph;


import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_updates.ObjectUpdate;

public abstract class ObjectNodeNtro 

	   extends        GenericDirectedNodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

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
	public String label() {
		StringBuilder builder = new StringBuilder();
		
		if(isList()) {

			builder.append("List");

		}else if(isMap()) {

			builder.append("Map");

		}else if(isUserDefinedObject()) {
			
			builder.append(type().getSimpleName());

		}else if(isSimpleValue()) {

			if(asSimpleValue().isString()) {
				
				builder.append('"');
				builder.append(getObject());
				builder.append('"');


			} else {

				builder.append(getObject());
			}
		}

		return builder.toString();
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

		}else {

			type = Void.class;
		}
		
		return type;
	}

	@Override
	public boolean isList() {
		return Ntro.reflectionService().isList(getObject());
	}


	@Override
	public boolean isMap() {
		return Ntro.reflectionService().isMap(getObject());
	}


	@Override
	public boolean isUserDefinedObject() {
		return Ntro.reflectionService().isUserDefinedObject(getObject());
	}

	@Override
	public boolean isSimpleValue() {
		return Ntro.reflectionService().isSimpleValue(getObject());
	}

	@Override
	public ObjectNodeSimpleValue asSimpleValue() {
		return (ObjectNodeSimpleValue) this;
	}

	@Override
	public boolean isNull() {
		return Ntro.reflectionService().isNull(getObject());
	}

	@Override
	public boolean isBoolean() {
		return Ntro.reflectionService().isBoolean(getObject());
	}

	@Override
	public boolean isString() {
		return Ntro.reflectionService().isString(getObject());
	}

	@Override
	public boolean asBoolean() {
		return Ntro.reflectionService().asBoolean(getObject());
	}

	@Override
	public String asString() {
		return Ntro.reflectionService().asString(getObject());
	}

	@Override
	public List<?> asList() {
		return Ntro.reflectionService().asList(getObject());
	}

	@Override
	public <I> List<I> asList(Class<I> itemClass) {
		return Ntro.reflectionService().asList(getObject(), itemClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Map<String, V> asMap(Class<V> valueClass) {
		return Ntro.reflectionService().asMap(getObject(), valueClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> asMap() {
		return Ntro.reflectionService().asMap(getObject());
	}

	@Override
	public Object asUserDefinedObject() {
		return Ntro.reflectionService().asUserDefinedObject(getObject());
	}

	@Override
	public <V> V asUserDefinedObject(Class<V> _class) {
		return Ntro.reflectionService().asUserDefinedObject(getObject(), _class);
	}

	@Override
	public boolean isNumber() {
		return Ntro.reflectionService().isNumber(getObject());
	}

	@Override
	public char asChar() {
		return Ntro.reflectionService().asChar(getObject());
	}

	@Override
	public int asInt() {
		return Ntro.reflectionService().asInt(getObject());
	}

	@Override
	public long asLong() {
		return Ntro.reflectionService().asLong(getObject());
	}

	@Override
	public float asFloat() {
		return Ntro.reflectionService().asFloat(getObject());
	}

	@Override
	public double asDouble() {
		return Ntro.reflectionService().asDouble(getObject());
	}
	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		if(isString()) {
			builder.append('"');
		}
		
		builder.append(asSimpleValue().asString());

		if(isString()) {
			builder.append('"');
		}
		
		
		return builder.toString();
		
	}
	
	

	@Override
	public ObjectUpdate asUpdates() {
		// TODO: describe the object as a sequence
		// of SET/INSERT/DELETE operations
		return null;
	}
}
