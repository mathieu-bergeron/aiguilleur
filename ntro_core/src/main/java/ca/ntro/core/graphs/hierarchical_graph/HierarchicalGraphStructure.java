package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface HierarchicalGraphStructure<NV extends NodeValue, EV extends EdgeValue> extends GenericGraphStructure<NV,EV> {

	void addSubNode(Direction direction, Node<NV> parentNode, Node<NV> subNode);

	<R> void reduceSubNodes(Node<NV> parentNode, Direction direction, ResultNtro<R> result, SubNodeReducer<NV,R> reducer);

}
