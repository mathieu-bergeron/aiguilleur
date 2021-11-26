package ca.ntro.core.graphs;

public interface WalkedStepReducer<NV extends NodeValue, 
                                   EV extends EdgeValue, 
                                   N extends Node<NV>,
                                   E extends Edge<EV>,
                                   R extends Object> {
	
	R reduceWalkedStep(R accumulator, WalkedStep<NV,EV,N,E> step) throws Throwable;

}
