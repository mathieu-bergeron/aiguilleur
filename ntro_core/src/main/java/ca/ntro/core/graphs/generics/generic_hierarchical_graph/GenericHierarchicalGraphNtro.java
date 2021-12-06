package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;

public class GenericHierarchicalGraphNtro <N extends GenericHierarchicalNode<N,E,SO>,
								    E extends Edge<N,E,SO>,
								    SO extends HierarchicalGraphSearchOptionsBuilder> 

       extends GenericGraphNtro<N,E,SO> 

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
