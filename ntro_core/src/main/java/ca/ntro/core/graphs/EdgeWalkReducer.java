package ca.ntro.core.graphs;

import java.util.List;

public interface EdgeWalkReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {

	R reduceEdgeWalk(R accumulator, List<Edge<EV>> walkedEdges, Node<NV> n) throws Throwable;

}
