package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.path.EdgeWalk;

public interface EdgeWalkReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {

	R reduceEdgeWalk(R accumulator, List<WalkedStep<NV,EV>> walkedSteps, EdgeWalk remainingEdgeWalk, Node<NV> n) throws Throwable;

}
