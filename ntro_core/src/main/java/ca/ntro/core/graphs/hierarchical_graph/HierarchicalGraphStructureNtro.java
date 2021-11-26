package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructureNtro;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.wrappers.result.ResultNtro;

public class       HierarchicalGraphStructureNtro<NV extends NodeValue, 
                                                  EV extends EdgeValue,
                                                  N extends Node<NV>,
                                                  E extends Edge<EV>> 
       extends     GenericGraphStructureNtro<NV,EV,N,E>
       implements  HierarchicalGraphStructure<NV,EV,N,E> {

	@Override
	public void addSubNode(Direction direction, 
			               Node<NV> parentNode, 
			               Node<NV> subNode) {
		
	}

	@Override
	public <R> void reduceSubNodes(Node<NV> parentNode, 
			                       Direction direction, 
			                       ResultNtro<R> result, 
			                       SubNodeReducer<NV, R> reducer) {
		
	}

	@Override
	protected EdgesForFromNode<NV, EV> edgesByDirection(Direction direction) {

		return null;
	}

	@Override
	protected EdgeId directedEdgeId(Node<NV> from, 
			                        EV edgeValue, 
			                        Node<NV> to) {

		return null;
	}

	@Override
	protected void memorizeDirectedEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		
	}
}
