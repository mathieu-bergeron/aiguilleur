package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;

public class GenericGraphNtro<N extends GenericNode<N,E,SO>,
                              E extends Edge<N,E,SO>,
                              SO extends GraphSearchOptionsBuilder> 

       extends GenericDirectedGraphNtro<N,E,SO> 

       implements GenericGraph<N,E,SO> {

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InternalGraphWriter<N, E, SO> internalGraphWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericGraphStructure<N, E, SO> graphStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SO defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
