package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;

public class       StepsUpDownNtro<NV extends NodeValue,
                                   EV extends EdgeValue,
                                   N extends Node<NV>,
                                   E extends Edge<EV>>

       implements  StepsUpDown<NV,EV,N,E> {

	@Override
	public boolean contains(Step<NV, EV, N, E> walkedStep) {

		return false;
	}

	@Override
	public void memorize(Step<NV, EV, N, E> walkedStep) {

	}

}
