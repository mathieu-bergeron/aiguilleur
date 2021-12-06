package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.ReachableNodeReducer;
import ca.ntro.core.graphs.generics.directed_graph.ReachableNodeVisitor;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNodeBuilderNtro;
import ca.ntro.core.wrappers.result.Result;

public class MockHierarchicalNode extends HierarchicalNodeBuilderNtro<MockHierarchicalNode, 
                                                                      MockHierarchicalEdge, 
                                                                      HierarchicalGraphSearchOptionsBuilder,
                                                                      MockHierarchicalNode> {

	public MockHierarchicalNode(NodeId nodeId,
			GenericDirectedGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, MockHierarchicalNode, GenericDirectedGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	@Override
	public boolean hasSubNodes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasParent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MockHierarchicalNode parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachSubNode(
			ReachableNodeVisitor<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachSubNode(HierarchicalGraphSearchOptionsBuilder options,
			ReachableNodeVisitor<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceSubNodes(R initialValue,
			ReachableNodeReducer<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceSubNodes(HierarchicalGraphSearchOptionsBuilder options, R initialValue,
			ReachableNodeReducer<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachParentNode(
			ReachableNodeVisitor<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachParentNode(HierarchicalGraphSearchOptionsBuilder options,
			ReachableNodeVisitor<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceParentNodes(R initialValue,
			ReachableNodeReducer<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceParentNodes(HierarchicalGraphSearchOptionsBuilder options, R initialValue,
			ReachableNodeReducer<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericNodeStructure<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}
