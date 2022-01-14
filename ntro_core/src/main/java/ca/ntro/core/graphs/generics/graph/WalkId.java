package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.path.Path;

public interface WalkId extends GenericWalk<EdgeType, WalkId> {
	
	Path toPath();
	

}
