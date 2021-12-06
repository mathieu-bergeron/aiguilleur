package ca.ntro.core.reflection;


import ca.ntro.core.graphs.generics.directed_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilder;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilderNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public class ObjectNodeJdk extends ObjectNodeNtro {
	

	public ObjectNodeJdk(ObjectGraph graph, LocalHeap localHeap, Object object, NodeId nodeId) {
		super(graph, localHeap, object, nodeId);
	}


	@Override
	protected ObjectGraphSearchOptionsBuilder defaultSearchOptions() {
		return new ObjectGraphSearchOptionsBuilderNtro();
	}

	@Override
	public boolean isStartNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected GenericNodeStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}



}
