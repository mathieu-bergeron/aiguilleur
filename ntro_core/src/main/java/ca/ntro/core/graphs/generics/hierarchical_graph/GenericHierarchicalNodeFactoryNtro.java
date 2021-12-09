package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNodeFactoryNtro;

public class       GenericHierarchicalNodeFactoryNtro<N extends GenericHierarchicalNode<N,E,SO>,
								                      E extends GenericEdge<N,E,SO>,
								                      SO extends HierarchicalSearchOptions> 

       extends     GenericNodeFactoryNtro<N,E,SO>

       implements  GenericHierarchicalNodeFactory<N,E,SO> {

}
