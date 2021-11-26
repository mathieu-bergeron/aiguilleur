package ca.ntro.core.graphs;

public interface WalkedStep<NV extends NodeValue, EV extends EdgeValue> {

	Direction direction();
	
	Node<NV> from();
	Edge<EV> edge();
	Node<NV> to();
	
	WalkedStepId id();
	

}
