package ca.ntro.core.graphs;

public interface WalkedStep<NV extends NodeValue, 
                            EV extends EdgeValue,
                            N extends Node<NV>,
                            E extends Edge<EV>> {

	Direction direction();
	
	N from();
	E edge();
	N to();
	
	WalkedStepId id();
	

}
