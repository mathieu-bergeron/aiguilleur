package ca.ntro.core.reflection;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;

public class ObjectGraphNull extends DirectedGraphNtro<ObjectValue, ReferenceValue, NodeNtro<ObjectValue>, EdgeNtro<ReferenceValue>> implements ObjectGraph {
}
