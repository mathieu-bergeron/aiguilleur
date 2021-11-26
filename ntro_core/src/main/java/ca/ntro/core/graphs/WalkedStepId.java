package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.EntityId;
import ca.ntro.core.path.Path;

public class WalkedStepId extends EntityId {
	
	public WalkedStepId(String rawPath) {
		super(rawPath);
	}

	public WalkedStepId(Path entityPath) {
		super(entityPath);
	}


	public static WalkedStepId fromKey(String key) {
		return new WalkedStepId(key);
	}
}
