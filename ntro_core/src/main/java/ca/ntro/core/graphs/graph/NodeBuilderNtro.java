package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilderNtro;

public class      NodeBuilderNtro<N extends Node<N,E>,
                                       E extends Edge<N,E>>

       extends    GenericNodeBuilderNtro<N,E,GraphSearchOptions,NodeBuilder<N,E>> 

       implements NodeBuilder<N,E> {


}
