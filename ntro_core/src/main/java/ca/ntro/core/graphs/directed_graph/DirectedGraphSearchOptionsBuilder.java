package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.graph.GraphSearchOptionsBuilder;

public interface DirectedGraphSearchOptionsBuilder extends GraphSearchOptionsBuilder {
	
	enum DirectedGraphDirection {
		FORWARD, BACKWARD;
	}
	
	void setDirections(DirectedGraphDirection[] directions);

}
