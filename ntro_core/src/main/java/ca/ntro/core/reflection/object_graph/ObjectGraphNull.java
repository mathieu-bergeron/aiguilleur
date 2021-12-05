package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;
import ca.ntro.core.wrappers.result.Result;

public class ObjectGraphNull implements ObjectGraph {

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObjectNode findNode(NodeId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachStartNode(NodeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceStartNodes(R initialValue,
			NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(NodeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue,
			NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachEdge(EdgeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue,
			EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectGraphSearchOptionsBuilder defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode findNode(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectUpdates objectAsUpdates(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
