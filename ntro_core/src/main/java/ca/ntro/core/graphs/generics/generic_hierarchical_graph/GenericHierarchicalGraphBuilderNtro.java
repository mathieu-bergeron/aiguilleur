package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;

public abstract class GenericHierarchicalGraphBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
											              E extends Edge<N,E,SO>,
											              SO extends HierarchicalSearchOptionsBuilder,
											              NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
											              G extends GenericHierarchicalGraph<N,E,SO>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,G> 

       implements     GenericHierarchicalGraphBuilder<N,E,SO,NB,G> {

}
