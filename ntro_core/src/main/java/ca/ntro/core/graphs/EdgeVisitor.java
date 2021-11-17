package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<NV extends NodeValue, EV extends EdgeValue> {
	
	void visitEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) throws Break;

}
