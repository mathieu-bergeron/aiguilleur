package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalGraphNtro;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNode;

public class HierarchicalDagNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                 E extends Edge<N,E,SO>,
                                 SO extends HierarchicalDagSearchOptionsBuilder>

                                 extends GenericHierarchicalGraphNtro<N,E,SO>

                                 implements HierarchicalDag<N,E,SO> {


}
