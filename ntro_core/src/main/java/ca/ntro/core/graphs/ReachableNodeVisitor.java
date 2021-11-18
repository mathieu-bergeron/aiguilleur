package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.exceptions.Break;

public interface ReachableNodeVisitor<NV extends NodeValue, EV extends EdgeValue> {

	void visitReachableNode(List<Edge<EV>> edgeWalked, Node<NV> n) throws Break;

}
