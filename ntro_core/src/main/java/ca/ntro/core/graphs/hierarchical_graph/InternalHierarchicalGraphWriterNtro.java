package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.writers.GraphWriter;

public class      InternalHierarchicalGraphWriterNtro<NV extends NodeValue, 
                                                      EV extends EdgeValue,
                                                      N extends Node<NV>,
                                                      E extends Edge<EV>>

       extends    InternalGraphWriterNtro<NV,EV,N,E,HierarchicalGraph<NV,EV,N,E>>

       implements InternalHierarchicalGraphWriter<NV,EV,N,E> {

	@Override
	public void write(GenericGraph<NV,EV,N,E> graph, GraphWriter writer) {
		writeClusters(graph, writer);

		super.write(graph, writer);
	}


	private void writeClusters(GenericGraph<NV,EV,N,E> graph, GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEdge(GraphWriter writer, Node<NV> from, Edge<EV> edge, Node<NV> to) {
		

	}

}
