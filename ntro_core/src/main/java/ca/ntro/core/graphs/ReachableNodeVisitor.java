package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableNodeVisitor<NV extends NodeValue, 
                                      EV extends EdgeValue,
                                      N extends Node<NV>,
                                      E extends Edge<EV>> {

	void visitReachableNode(List<WalkedStep<NV,EV,N,E>> walkedSteps, N n) throws Throwable;

}
