package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.identifyers.Label;
import ca.ntro.core.identifyers.Name;

public interface EdgeType extends Label, GenericStep {

	Direction direction();
	Name name();

	boolean equalsUndirected(EdgeType other);

}
