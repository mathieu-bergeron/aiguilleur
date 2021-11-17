package ca.ntro.core.graphs.generic_graph;

public interface Edge<EV extends EdgeValue> {
	
	EdgeId id();

	EV value();

}
