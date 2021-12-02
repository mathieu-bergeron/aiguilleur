package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.NodeBuilder;

public interface HierarchicalNodeBuilder<N  extends HierarchicalNode<N,E,SO>,
                                         E  extends Edge<N,E,SO>,
                                         SO extends SearchOptionsNtro>

       extends   NodeBuilder<N,E,SO> {

	void addSubNode(N subNode);

}
