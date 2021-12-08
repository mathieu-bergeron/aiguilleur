package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;
import ca.ntro.core.graphs.generics.hierarchical_graph.InternalHierarchicalGraphWriterNtro;

public class      InternalHierarchicalDagWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                      E extends GenericEdge<N,E,SO>,
													  SO extends HierarchicalDagSearchOptionsBuilder>

       extends    InternalHierarchicalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalDagWriter<N,E,SO> {

}
