package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNode;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.InternalHierarchicalGraphWriterNtro;

public class      InternalHierarchicalDagWriterNtro<N extends HierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends HierarchicalDagSearchOptionsBuilder>

       extends    InternalHierarchicalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalDagWriter<N,E,SO> {

}
