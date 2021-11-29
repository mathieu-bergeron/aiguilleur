package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.writers.GraphWriter;

public class      InternalHierarchicalGraphWriterNtro<N extends HierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends SearchOptions>

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalGraphWriter<N,E,SO> {

	@Override
	public void write(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		writeClusters(graph, writer);

		super.write(graph, writer);
	}

	private void writeClusters(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEdge(GraphWriter writer, E edge) {

		// FIXME
		super.writeEdge(writer, edge);
	}

}
