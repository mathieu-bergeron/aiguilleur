package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphNtro;

public class      HierarchicalDagNtro<N extends HierarchicalDagNode<N,E>,
                                      E extends HierarchicalDagEdge<N,E>>



       extends    GenericHierarchicalGraphNtro<N,E,HierarchicalDagSearchOptions,HierarchicalDagWriterOptions>

       implements HierarchicalDag<N,E> {

	@Override
	protected HierarchicalDagSearchOptions newDefaultSearchOptionsInstance() {
		return new HierarchicalDagSearchOptionsNtro();
	}

	@Override
	protected HierarchicalDagWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new HierarchicalDagWriterOptionsNtro();
	}


}
