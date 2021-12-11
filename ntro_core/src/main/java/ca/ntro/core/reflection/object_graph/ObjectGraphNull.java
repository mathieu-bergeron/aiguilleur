package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.EdgeReducer;
import ca.ntro.core.graphs.generics.graph.EdgeVisitor;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.NodeReducer;
import ca.ntro.core.graphs.generics.graph.NodeVisitor;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;
import ca.ntro.core.stream.Stream;
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
	public void write(ObjectGraphWriterOptions options, GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObjectGraphSearchOptions defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectGraphWriterOptions defaultGraphWriterOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode findNode(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode findNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectNode> startNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachStartNode(NodeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceStartNodes(R initialValue,
			NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectNode> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(NodeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue,
			NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ReferenceEdge> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachEdge(EdgeVisitor<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue,
			EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectUpdates objectAsUpdates(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
