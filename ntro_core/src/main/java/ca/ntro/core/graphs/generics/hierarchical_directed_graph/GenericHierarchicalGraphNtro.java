package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;

public class GenericHierarchicalGraphNtro <N extends GenericHierarchicalNode<N,E,SO>,
								    E extends Edge<N,E,SO>,
								    SO extends HierarchicalGraphSearchOptionsBuilder> 

       extends GenericDirectedGraphNtro<N,E,SO> 

       implements GenericHierarchicalGraph<N,E,SO>{


	@Override
	public SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}

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
	public InternalGraphWriter<N, E, SO> internalGraphWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericGraphStructure<N, E, SO> graphStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}
