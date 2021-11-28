package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;

public interface StepsInDirection<NV extends NodeValue,
                                  EV extends EdgeValue,
                                  N extends Node<NV>,
                                  E extends Edge<EV>> {

	boolean contains(Step<NV, EV, N, E> walkedStep);

	void memorize(Step<NV, EV, N, E> walkedStep);

}
