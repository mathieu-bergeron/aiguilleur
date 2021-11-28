package ca.ntro.core.graphs;

import java.util.List;

public interface StepsVisitor<NV extends NodeValue, 
                              EV extends EdgeValue,
                              N extends Node<NV>,
                              E extends Edge<EV>> {

	void visitStep(List<Step<NV,EV,N,E>> walkedSteps, List<StepId> remainingSteps, Node<NV> currentNode) throws Throwable;

}
