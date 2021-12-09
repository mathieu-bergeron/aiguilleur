package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeNtro;

public class      HierarchicalGraphNodeNtro<N extends HierarchicalGraphNode<N,E>,
                                            E extends HierarchicalGraphEdge<N,E>> 

       extends    GenericHierarchicalNodeNtro<N,E,HierarchicalGraphSearchOptions>

       implements HierarchicalGraphNode<N,E> {

	public HierarchicalGraphNodeNtro() {
		super();
	}

	public HierarchicalGraphNodeNtro(NodeId id) {
		super(id);
	}
}
