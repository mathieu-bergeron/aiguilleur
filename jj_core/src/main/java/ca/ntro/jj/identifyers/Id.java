package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.Path;

public interface Id {
	
	String toKey();  // e.g.   models¤QueueModel^alice¤2021, where categoryId is models¤QueueModel and entityId is alice¤2021
	Path toPath();   // e.g.  /models/QueueModel/alice¤2021, where categoryPath is /models/QueueModel and entityId is alice¤2021

}
