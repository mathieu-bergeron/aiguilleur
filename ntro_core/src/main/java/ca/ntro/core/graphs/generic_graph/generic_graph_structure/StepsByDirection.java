package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.WalkedStep;

public interface StepsByDirection <NV extends NodeValue, 
                               EV extends EdgeValue,
                               N extends Node<NV>,
                               E extends Edge<EV>> {

	void memorize(WalkedStep<NV,EV,N,E> walkedStep);
	boolean contains(WalkedStep<NV,EV,N,E> walkedStep);
	
}
