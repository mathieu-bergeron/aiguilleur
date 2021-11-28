package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericEdge;
import ca.ntro.core.identifyers.Label;
import ca.ntro.core.identifyers.Name;

public interface EdgeName extends Label, GenericEdge {

	Direction direction();
	Name name();

}
