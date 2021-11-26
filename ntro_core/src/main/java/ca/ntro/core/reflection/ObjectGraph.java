package ca.ntro.core.reflection;

import ca.ntro.core.graphs.directed_graph.DirectedGraph;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;

public interface ObjectGraph extends DirectedGraph<ObjectValue, ReferenceValue, NodeNtro<ObjectValue>, EdgeNtro<ReferenceValue>> {

}
