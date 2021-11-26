package ca.ntro.core.reflection;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.directed_graph.DirectedGraph;

public interface ObjectGraph extends DirectedGraph<ObjectValue, ReferenceValue, Node<ObjectValue>, Edge<ReferenceValue>> {

}
