package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.identifyers.EntityId;
import ca.ntro.core.path.Path;

public class NodeId extends EntityId {
	
	public NodeId(String rawPath) {
		super(rawPath);
	}

	public NodeId(Path nodePath) {
		super(nodePath);
	}

	public static NodeId fromKey(String key) {
		return new NodeId(key);
	}
}
