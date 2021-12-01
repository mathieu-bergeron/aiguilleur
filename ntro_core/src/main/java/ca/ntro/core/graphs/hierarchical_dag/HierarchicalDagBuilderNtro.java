package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public class   HierarchicalDagBuilderNtro<N extends HierarchicalNode<N,E,SO>,
                                          E extends Edge<N,E,SO>,
                                          SO extends HierarchicalDagSearchOptions,
                                          G extends HierarchicalDag<N,E,SO>>

       extends GenericGraphBuilderNtro<N,E,SO,G>

	   implements HierarchicalDagBuilder<N,E,SO,G> {

	public HierarchicalDagBuilderNtro() {
		super();
	}

	public HierarchicalDagBuilderNtro(String graphName) {
		super(graphName);
	}

}
