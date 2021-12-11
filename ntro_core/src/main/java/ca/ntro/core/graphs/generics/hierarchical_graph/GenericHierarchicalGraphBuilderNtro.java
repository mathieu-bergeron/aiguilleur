package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public abstract class GenericHierarchicalGraphBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
											              E extends GenericEdge<N,E,SO>,
											              SO extends HierarchicalSearchOptions,
											              NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>,
											              GO extends HierarchicalGraphWriterOptions,
											              G extends GenericHierarchicalGraph<N,E,SO,GO>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,GO,G> 

       implements     GenericHierarchicalGraphBuilder<N,E,SO,NB,GO,G> {

}
