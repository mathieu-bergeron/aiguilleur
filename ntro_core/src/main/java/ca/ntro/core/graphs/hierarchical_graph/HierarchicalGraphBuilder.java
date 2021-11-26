package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface HierarchicalGraphBuilder<NV extends NodeValue, EV extends EdgeValue> 
       extends   GenericGraphBuilder<NV,EV, HierarchicalGraphStructure<NV,EV>, HierarchicalGraph<NV,EV>> {

	void addSubNode(Node<NV> parentNode, Node<NV> subNode);

}
