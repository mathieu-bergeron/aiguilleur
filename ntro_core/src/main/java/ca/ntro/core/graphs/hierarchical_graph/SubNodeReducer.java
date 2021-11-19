package ca.ntro.core.graphs.hierarchical_graph;

import java.util.List;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public interface SubNodeReducer<NV extends NodeValue, R extends Object> {
	
	R reduceSubNode(R accumulator, List<Node<NV>> parentNodes, Node<NV> n) throws Throwable;

}
