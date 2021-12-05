package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.wrappers.result.Result;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder>{


	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {

		return new MockEdge(fromNode, edgeType, toNode);
	}

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
	public MockNode findNode(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MockNode findNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachStartNode(NodeVisitor<MockNode, MockEdge, GraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceStartNodes(R initialValue,
			NodeReducer<MockNode, MockEdge, GraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(NodeVisitor<MockNode, MockEdge, GraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue,
			NodeReducer<MockNode, MockEdge, GraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachEdge(EdgeVisitor<MockNode, MockEdge, GraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue,
			EdgeReducer<MockNode, MockEdge, GraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MockNodeBuilder createNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}





}
