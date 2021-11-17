package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.directed_graph.DirectedGraph;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.NodeValue;

public interface Dag<NV extends NodeValue,EV extends EdgeValue> extends DirectedGraph<NV,EV> {

}
