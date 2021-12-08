package ca.ntro.core.graphs.common;

import ca.ntro.core.graphs.generics.graph.GenericStep;
import ca.ntro.core.identifyers.Label;
import ca.ntro.core.identifyers.Name;

public interface EdgeType extends Label, GenericStep {

	Direction direction();
	Name name();

	boolean equalsUndirected(EdgeType other);

}
