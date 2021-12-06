package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;

public class HierarchicalGraphNtro <N extends HierarchicalNode<N,E,SO>,
								    E extends Edge<N,E,SO>,
								    SO extends HierarchicalGraphSearchOptionsBuilder> 

       extends GenericGraphNtro<N,E,SO> 

       implements HierarchicalGraph<N,E,SO>{

	public HierarchicalGraphNtro(GraphId id, GenericGraphStructure<N, E, SO> graphStructure) {
		super(id, graphStructure);
	}

	@Override
	public SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}

}
