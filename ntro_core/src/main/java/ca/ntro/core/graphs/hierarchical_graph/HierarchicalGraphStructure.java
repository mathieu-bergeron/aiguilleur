package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface HierarchicalGraphStructure<NV extends NodeValue, 
                                            EV extends EdgeValue,
                                            N extends Node<NV>,
                                            E extends Edge<EV>> extends GenericGraphStructure<NV,EV,N,E> {

	void addSubNode(Direction direction, Node<NV> parentNode, Node<NV> subNode);

	<R> void reduceSubNodes(Node<NV> parentNode, Direction direction, ResultNtro<R> result, SubNodeReducer<NV,R> reducer);

}
