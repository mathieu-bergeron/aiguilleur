package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.graph.GraphStructureNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class       HierarchicalGraphStructureNtro<NV extends NodeValue, 
                                                  EV extends EdgeValue,
                                                  N extends HierarchicalNode<NV>,
                                                  E extends Edge<EV>> 

       extends     GraphStructureNtro<NV,EV,N,E>

       implements  HierarchicalGraphStructure<NV,EV,N,E> {

	@SuppressWarnings("unchecked")
	@Override
	public N createNode(NV nodeValue) {

		NodeId nodeId = new NodeId(nodeValue.name().toKey());

		N node = (N) new HierarchicalNodeNtro<NV>(nodeId, nodeValue);
		
		return node;
	}

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
}
