package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.reflection.object_updates.ObjectUpdate;
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
	public Stream<ObjectNode> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ReferenceEdge> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectUpdate> objectAsUpdates(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
