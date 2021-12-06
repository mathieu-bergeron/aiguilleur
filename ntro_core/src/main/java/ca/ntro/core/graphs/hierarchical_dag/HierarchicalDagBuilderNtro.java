package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalNode;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalNodeBuilder;

public abstract class HierarchicalDagBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                 E extends Edge<N,E,SO>,
                                                 SO extends HierarchicalDagSearchOptionsBuilder,
                                                 NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
                                                 G extends HierarchicalDag<N,E,SO>>

       extends GenericDirectedGraphBuilderNtro<N,E,SO,NB,G>

	   implements HierarchicalDagBuilder<N,E,SO,NB,G> {

	public HierarchicalDagBuilderNtro() {
		super();
	}

	public HierarchicalDagBuilderNtro(String graphName) {
		super(graphName);
	}

}
