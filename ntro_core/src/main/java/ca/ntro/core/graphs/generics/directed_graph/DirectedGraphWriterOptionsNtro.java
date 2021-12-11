package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GraphWriterOptionsNtro;

public class      DirectedGraphWriterOptionsNtro 

       extends    GraphWriterOptionsNtro

       implements DirectedGraphWriterOptions {

	@Override
	public boolean isDirected() {
		return true;
	}

}
