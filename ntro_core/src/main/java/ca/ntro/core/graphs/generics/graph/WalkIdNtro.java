package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.path.Path;

public class WalkIdNtro 

       extends GenericWalkNtro<EdgeType, WalkId>

       implements WalkId {

	@Override
	public Path toPath() {
		Path path = Path.emptyPath();
		
		edges().forEach(edge -> {
			
			path.addName(edge.name().toString());
		});
		
		return path;
	}

}
