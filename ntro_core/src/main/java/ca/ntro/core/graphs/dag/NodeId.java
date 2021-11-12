package ca.ntro.core.graphs.dag;

import ca.ntro.core.identifyers.Id;

public class NodeId extends Id {
	
	public NodeId(String id) {
		super(id);
	}
	
	public static NodeId fromKey(String key) {
		return new NodeId(key);
	}
}
