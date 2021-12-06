package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeNtro;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectNodeNtro 

	   extends        GenericNodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>

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


	@Override
	public GenericDirectedGraph<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> parentGraph() {
		return getGraph();
	}

	@Override
	public Object object() {
		return getObject();
	}

	@Override
	public ObjectUpdates asUpdates() {
		// TODO: describe the object as a sequence
		// of SET/INSERT/DELETE operations
		return null;
	}
}
