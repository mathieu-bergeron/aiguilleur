package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.reflection.ObjectValue;
import ca.ntro.core.reflection.ReferenceValue;

public class ObjectGraphNull extends DirectedGraphNtro<ObjectValue, ReferenceValue, Node<ObjectValue>, Edge<ReferenceValue>> implements ObjectGraph {
}
