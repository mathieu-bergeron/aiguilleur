package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generics.hierarchical_graph.InternalHierarchicalGraphWriterNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptions;

public class      InternalHierarchicalDagWriterNtro<N extends HierarchicalDagNode<N,E>,
                                                    E extends HierarchicalDagEdge<N,E>>

       extends    InternalHierarchicalGraphWriterNtro<N,E,HierarchicalDagSearchOptions>

       implements InternalHierarchicalDagWriter<N,E> {
	
	
	@Override
	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(true);
	}

}
