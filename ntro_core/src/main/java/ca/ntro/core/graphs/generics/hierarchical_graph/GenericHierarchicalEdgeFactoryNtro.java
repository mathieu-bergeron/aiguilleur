package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericEdgeFactory;
import ca.ntro.core.graphs.generics.graph.GenericEdgeFactoryNtro;

public class GenericHierarchicalEdgeFactoryNtro<N extends GenericHierarchicalNode<N,E,SO>,
								                E extends GenericEdge<N,E,SO>,
								                SO extends HierarchicalSearchOptions> 

       extends GenericEdgeFactoryNtro<N,E,SO>

       implements GenericEdgeFactory<N,E,SO> {

}
