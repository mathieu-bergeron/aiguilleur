package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public abstract class GenericDirectedGraphBuilderNtro<N extends  GenericDirectedNode<N,E,SO>,
                                                      E extends  GenericDirectedEdge<N,E,SO>,
                                                      SO extends DirectedGraphSearchOptions,
									                  NB extends GenericDirectedNodeBuilder<N,E,SO,NB>,
									                  GO extends DirectedGraphWriterOptions,
                                                      G extends  GenericDirectedGraph<N,E,SO,GO>> 

       extends        GenericGraphBuilderNtro<N,E,SO,NB,GO,G> 

       implements     GenericGraphBuilder<N,E,SO,NB,GO,G>{

}
