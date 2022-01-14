package ca.ntro.core.reflection;


import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptionsNtro;
import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptions;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsNtro;
import ca.ntro.core.reflection.object_graph.GenericObjectNode;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectNodeJdk extends ObjectNodeNtro {
	
	
	private boolean isStartNode = false;

	public ObjectNodeJdk(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId, boolean isStartNode) {
		super(graph, localHeap, object, nodeId);
		
		this.isStartNode = isStartNode;
	}


	@Override
	protected ObjectGraphSearchOptions defaultSearchOptions() {
		return new ObjectGraphSearchOptionsNtro();
	}

	@Override
	public boolean isStartNode() {
		return isStartNode;
	}

	@Override
	protected GenericNodeStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> nodeStructure() {
		return new ObjectNodeStructureJdk((ObjectNodeNtro) this, (ObjectGraphNtro) getGraph());
	}



}
