package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeFactoryNtro;

public class HierarchicalDagNodeFactoryNtro<N extends HierarchicalDagNode<N,E>,
                                            E extends HierarchicalDagEdge<N,E>> 

       extends   GenericHierarchicalNodeFactoryNtro<N,E,HierarchicalDagSearchOptions> 

       implements HierarchicalDagNodeFactory<N,E> {


}
