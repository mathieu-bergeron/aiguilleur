package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.identifyers.Label;
import ca.ntro.core.identifyers.Name;

public interface EdgeType extends Label, GenericEdge {

	Direction direction();
	Name name();

}
