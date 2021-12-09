package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public class HierarchicalDagBuilderNtro<N extends HierarchicalDagNode<N,E>,
                                        E extends HierarchicalDagEdge<N,E>>

       extends GenericGraphBuilderNtro<N,
                                       E,
                                       HierarchicalDagSearchOptions,
                                       HierarchicalDagNodeBuilder<N,E>,
                                       HierarchicalDag<N,E>>

	   implements HierarchicalDagBuilder<N,E> {

	@Override
	protected HierarchicalDag<N, E> newGraphInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected HierarchicalDagNodeBuilder<N, E> newNodeBuilderInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
