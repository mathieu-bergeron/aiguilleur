package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.HierarchicalGraphWriterOptionsNtro;

public class      HierarchicalDagWriterOptionsNtro 

       extends    HierarchicalGraphWriterOptionsNtro

       implements HierarchicalDagWriterOptions {

	@Override
	public boolean isDirected() {
		return true;
	}

}
