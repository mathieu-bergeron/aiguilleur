package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilder;

public abstract class HierarchicalDagBuilderNtro<N extends HierarchicalNode<N,E,SO>,
                                                 E extends Edge<N,E,SO>,
                                                 SO extends HierarchicalDagSearchOptionsBuilder,
                                                 NB extends HierarchicalNodeBuilder<N,E,SO,NB>,
                                                 G extends HierarchicalDag<N,E,SO>>

       extends GenericGraphBuilderNtro<N,E,SO,NB,G>

	   implements HierarchicalDagBuilder<N,E,SO,NB,G> {

	public HierarchicalDagBuilderNtro() {
		super();
	}

	public HierarchicalDagBuilderNtro(String graphName) {
		super(graphName);
	}

}
