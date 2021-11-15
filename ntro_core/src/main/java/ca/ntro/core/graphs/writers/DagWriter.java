package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.graph.Edge;
import ca.ntro.core.graphs.graph.Node;

public interface DagWriter<N extends Node, E extends Edge> {
	
	void write(Dag<N,E> dag);

}
