package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.Node;

public interface DagWriter<N extends Node, E extends Edge> {
	
	void write(Dag<N,E> dag);

}
