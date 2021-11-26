package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.GraphWriter;

public interface InternalGraphWriter<NV extends NodeValue, 
                                     EV extends EdgeValue,
                                     N extends Node<NV>,
                                     E extends Edge<EV>> {

	void write(GenericGraph<NV,EV,N,E> graph, GraphWriter writer);

}
