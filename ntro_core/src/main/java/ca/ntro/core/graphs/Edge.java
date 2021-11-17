package ca.ntro.core.graphs;

public interface Edge<EV extends EdgeValue> {
	
	EdgeId id();

	EV value();

}
