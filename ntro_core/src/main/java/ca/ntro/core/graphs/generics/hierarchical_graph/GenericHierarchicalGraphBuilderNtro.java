package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public abstract class GenericHierarchicalGraphBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
											              E extends GenericEdge<N,E,SO>,
											              SO extends HierarchicalSearchOptions,
											              NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
											              G extends GenericHierarchicalGraph<N,E,SO>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,G> 

       implements     GenericHierarchicalGraphBuilder<N,E,SO,NB,G> {

}
