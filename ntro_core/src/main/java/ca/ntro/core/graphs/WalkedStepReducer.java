package ca.ntro.core.graphs;

public interface WalkedStepReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceWalkedStep(R accumulator, WalkedStep<NV, EV> step) throws Throwable;

}
