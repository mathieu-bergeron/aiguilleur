package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.path.EdgeWalk;

public interface EdgeWalkVisitor<NV extends NodeValue, EV extends EdgeValue> {

	void visitEdgeWalk(List<Edge<EV>> walkedEdges, EdgeWalk remainingWalk, Node<NV> n) throws Throwable;

}
