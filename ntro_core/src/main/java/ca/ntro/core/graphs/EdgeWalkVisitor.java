package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.path.EdgeWalk;

public interface EdgeWalkVisitor<NV extends NodeValue, EV extends EdgeValue> {

	void visitEdgeWalk(List<WalkedStep<NV,EV>> walkedSteps, EdgeWalk remainingEdgeWalk, Node<NV> n) throws Throwable;

}
