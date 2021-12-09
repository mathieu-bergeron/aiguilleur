package ca.ntro.core.reflection;


import ca.ntro.core.graphs.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.directed_graph.DirectedSearchOptionsNtro;
import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ObjectNodeId;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectNodeJdk extends ObjectNodeNtro {
	
	
	private boolean isStartNode = false;

	public ObjectNodeJdk(ObjectGraph graph, LocalHeap localHeap, Object object, ObjectNodeId nodeId, boolean isStartNode) {
		super(graph, localHeap, object, nodeId);
		
		this.isStartNode = isStartNode;
	}


	@Override
	protected DirectedSearchOptions defaultSearchOptions() {
		return new DirectedSearchOptionsNtro();
	}

	@Override
	public boolean isStartNode() {
		return isStartNode;
	}

	@Override
	protected GenericNodeStructure<ObjectNode, ReferenceEdge, DirectedSearchOptions> nodeStructure() {
		return new ObjectNodeStructureJdk((ObjectNodeNtro) this, (ObjectGraphNtro) getGraph());
	}



}
