package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeNtro;

public class      HierarchicalGraphNodeNtro<N extends HierarchicalGraphNode<N,E>,
                                            E extends HierarchicalGraphEdge<N,E>> 

       extends    GenericHierarchicalNodeNtro<N,E,HierarchicalGraphSearchOptionsBuilder>

       implements HierarchicalGraphNode<N,E> {

	public HierarchicalGraphNodeNtro() {
		super();
	}

	public HierarchicalGraphNodeNtro(NodeId id) {
		super(id);
	}
}
