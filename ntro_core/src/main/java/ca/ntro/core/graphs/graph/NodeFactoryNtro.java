package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeFactoryNtro;

public class NodeFactoryNtro<N extends Node<N,E>,
                             E extends Edge<N,E>>

       extends GenericNodeFactoryNtro<N,E,GraphSearchOptions> 

       implements NodeFactory<N,E> {

}
