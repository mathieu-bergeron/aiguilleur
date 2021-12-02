package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;
import ca.ntro.core.graphs.hierarchical_graph.InternalHierarchicalGraphWriter;

public interface InternalHierarchicalDagWriter<N extends HierarchicalNode<N,E,SO>,
 											   E extends Edge<N,E,SO>,
 											   SO extends HierarchicalDagSearchOptionsBuilder>

       extends InternalHierarchicalGraphWriter<N,E,SO> {

}
